package fei.uv.mx.deliveryapp.DTOs;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.processing.Pattern;

import java.time.LocalTime;
import java.util.List;

public class RestaurantRequestDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    private String nameRestaurant;
    private LocalTime openTime;
    private LocalTime closeTime;
    private String imagePath;
    private String imageLogoPath;
    private List<String> categories;

    public String getNameRestaurant() {
        return nameRestaurant;
    }

    public void setNameRestaurant(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }

    public LocalTime getOpenTime() {
        return openTime;
    }

    public void setOpenTime(LocalTime openTime) {
        this.openTime = openTime;
    }

    public LocalTime getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(LocalTime closeTime) {
        this.closeTime = closeTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImageLogoPath() {
        return imageLogoPath;
    }

    public void setImageLogoPath(String imageLogoPath) {
        this.imageLogoPath = imageLogoPath;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
