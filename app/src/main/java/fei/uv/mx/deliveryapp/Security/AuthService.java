package fei.uv.mx.deliveryapp.Security;

import org.json.JSONObject;
import fei.uv.mx.deliveryapp.Models.Session;
import fei.uv.mx.deliveryapp.Models.User;
import fei.uv.mx.deliveryapp.Repositories.SessionRepository;
import fei.uv.mx.deliveryapp.Repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

import java.util.Date;

@Service
public class AuthService {

    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expiration}")
    private long jwtExpirationMs;

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public String authenticate(String username, String password, HttpServletRequest request) {
        Optional<User> optionalUser = userRepository.findByEmailAndPassword(username, password);
        if (optionalUser.isPresent()) {
            String token = createToken(username);
            Session session = new Session();
            session.setUser(optionalUser.get());
            session.setToken(token);
            session.setSessionDate(LocalDateTime.now());
            String ipAddress = request.getRemoteAddr();
            session.setIpAddress(ipAddress);
            String location = getLocationFromIP(ipAddress);
            session.setLocation(location);
            sessionRepository.save(session);
            return token;
        } else {
            throw new RuntimeException("Credenciales incorrectas");
        }
    }

    private String getLocationFromIP(String ipAddress) {

        try {
            String apiUrl = "http://ip-api.com/json/" + ipAddress;
            JSONObject json = doGetRequest(apiUrl);
            double lat = json.optDouble("lat", Double.NaN);
            double lon = json.optDouble("lon", Double.NaN);
            String city = json.optString("city", "Desconocida");
            String country = json.optString("country", "Desconocido");

            if (!Double.isNaN(lat) && !Double.isNaN(lon)) {
                String nominatimUrl = "https://nominatim.openstreetmap.org/reverse?format=json&lat=" + lat + "&lon=" + lon;
                JSONObject nomJson = doGetRequest(nominatimUrl);
                JSONObject address = nomJson.optJSONObject("address");
                if (address != null) {
                    String road = address.optString("road", "");
                    String suburb = address.optString("suburb", "");
                    String state = address.optString("state", "");
                    return String.format("Road: %s, Suburb: %s, City: %s, State: %s, Country: %s",
                            road, suburb, city, state, country);
                } else {
                    return city + ", " + country;
                }
            } else {
                return city + ", " + country;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Ubicación desconocida";
        }

    }

    private JSONObject doGetRequest(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();
        if (status == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();
            return new JSONObject(content.toString());
        } else {
            conn.disconnect();
            throw new RuntimeException("Error en la solicitud GET a: " + urlStr + " Código: " + status);
        }
    }

    private String createToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret.getBytes())
                .compact();
    }

    public long getJwtExpirationMs() {
        return jwtExpirationMs;
    }

    public String getJwtFromCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwtToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Firma del token no válida: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Token no válido: " + e.getMessage(), e);
        }
        return false;
    }

}
