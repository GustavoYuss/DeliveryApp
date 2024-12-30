package fei.uv.mx.deliveryapp.Seeders;

import fei.uv.mx.deliveryapp.Models.Dish;
import fei.uv.mx.deliveryapp.Models.DishType;
import fei.uv.mx.deliveryapp.Models.Restaurant;
import fei.uv.mx.deliveryapp.Repositories.DishRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DishSeeder implements CommandLineRunner {
    private DishRepository dishRepository;

    public DishSeeder(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        /*
        Restaurant restaurant1 = new Restaurant();
        restaurant1.setId(1);

        DishType dishType = new DishType();
        dishType.setId(1);

        Dish dish1 = new Dish();
        dish1.setName("Classic Burger");
        dish1.setDescription("Una jugosa hamburguesa de res con lechuga, tomate y queso, servida con papas fritas.");
        dish1.setNormalPrice(new BigDecimal("120.00"));
        dish1.setOfferPrice(new BigDecimal("110.00"));
        dish1.setImagePath("https://brookrest.com/wp-content/uploads/2020/05/AdobeStock_282247995-scaled.jpeg");
        dish1.setRestaurant(restaurant1);
        dish1.setDishType(dishType);

        Dish dish2 = new Dish();
        dish2.setName("Margarita Pizza");
        dish2.setDescription("Pizza italiana tradicional con salsa de tomate fresca, mozzarella y albahaca.");
        dish2.setNormalPrice(new BigDecimal("150.00"));
        dish2.setOfferPrice(new BigDecimal("140.00"));
        dish2.setImagePath("https://cdn.apartmenttherapy.info/image/fetch/f_auto,q_auto:eco,w_1460/https://storage.googleapis.com/gen-atmedia/3/2012/07/f2203c0e403286947dcf80815b656236fec71e88.jpeg");
        dish2.setRestaurant(restaurant1);
        dish2.setDishType(dishType);

        Dish dish3 = new Dish();
        dish3.setName("Caesar Salad");
        dish3.setDescription("Lechuga romana crujiente con aderezo César, crutones y queso parmesano.");
        dish3.setNormalPrice(new BigDecimal("90.00"));
        dish3.setOfferPrice(new BigDecimal("80.00"));
        dish3.setImagePath("https://shwetainthekitchen.com/wp-content/uploads/2022/09/vegetarian-caesar-salad.jpg");
        dish3.setRestaurant(restaurant1);
        dish3.setDishType(dishType);

        Dish dish4 = new Dish();
        dish4.setName("Spaghetti Carbonara");
        dish4.setDescription("Pasta mezclada en una salsa cremosa con panceta y queso parmesano.");
        dish4.setNormalPrice(new BigDecimal("130.00"));
        dish4.setOfferPrice(new BigDecimal("120.00"));
        dish4.setImagePath("https://www.cookingclassy.com/wp-content/uploads/2020/10/spaghetti-carbonara-01.jpg");
        dish4.setRestaurant(restaurant1);
        dish4.setDishType(dishType);

        Dish dish5 = new Dish();
        dish5.setName("Grilled Salmon");
        dish5.setDescription("Filete de salmón a la parrilla con espárragos y salsa de mantequilla al limón.");
        dish5.setNormalPrice(new BigDecimal("200.00"));
        dish5.setOfferPrice(new BigDecimal("190.00"));
        dish5.setImagePath("https://th.bing.com/th/id/OIP.clF7L2ygVn8PYj4S7jlWKQHaHa?rs=1&pid=ImgDetMain");
        dish5.setRestaurant(restaurant1);
        dish5.setDishType(dishType);

        Dish dish6 = new Dish();
        dish6.setName("Tacos al Pastor");
        dish6.setDescription("Tacos mexicanos tradicionales con carne de cerdo marinada, piña y cilantro fresco.");
        dish6.setNormalPrice(new BigDecimal("85.00"));
        dish6.setOfferPrice(new BigDecimal("75.00"));
        dish6.setImagePath("https://th.bing.com/th/id/R.d4a1d8383becbdaa44c49805a3dd66d9?rik=Kj3cL9QgriGcOA&pid=ImgRaw&r=0");
        dish6.setRestaurant(restaurant1);
        dish6.setDishType(dishType);

        Dish dish7 = new Dish();
        dish7.setName("Chicken Curry");
        dish7.setDescription("Curry indio picante con trozos de pollo tierno y arroz basmati.");
        dish7.setNormalPrice(new BigDecimal("140.00"));
        dish7.setOfferPrice(new BigDecimal("130.00"));
        dish7.setImagePath("https://th.bing.com/th/id/OIP.xCr9Xcccv2byqjl1ig29UAHaHa?rs=1&pid=ImgDetMain");
        dish7.setRestaurant(restaurant1);
        dish7.setDishType(dishType);

        Dish dish8 = new Dish();
        dish8.setName("Sushi Platter");
        dish8.setDescription("Una variedad de rollos de sushi y sashimi frescos, servidos con salsa de soya y wasabi.");
        dish8.setNormalPrice(new BigDecimal("250.00"));
        dish8.setOfferPrice(new BigDecimal("240.00"));
        dish8.setImagePath("https://th.bing.com/th/id/R.6de258265d94817d54ead50b98143585?rik=P6vuSKmgH%2fNozw&pid=ImgRaw&r=0");
        dish8.setRestaurant(restaurant1);
        dish8.setDishType(dishType);

        Dish dish9 = new Dish();
        dish9.setName("Vegan Bowl");
        dish9.setDescription("Un bowl saludable con quinoa, vegetales asados, aguacate y aderezo de tahini.");
        dish9.setNormalPrice(new BigDecimal("110.00"));
        dish9.setOfferPrice(new BigDecimal("100.00"));
        dish9.setImagePath("https://th.bing.com/th/id/R.9c5e21f55418880e8bc257c2883a2e38?rik=VgaikY4vG8C4iA&pid=ImgRaw&r=0");
        dish9.setRestaurant(restaurant1);
        dish9.setDishType(dishType);

        Dish dish10 = new Dish();
        dish10.setName("BBQ Ribs");
        dish10.setDescription("Costillas de cerdo cocinadas a fuego lento con salsa barbacoa, servidas con ensalada de col.");
        dish10.setNormalPrice(new BigDecimal("180.00"));
        dish10.setOfferPrice(new BigDecimal("170.00"));
        dish10.setImagePath("https://th.bing.com/th/id/OIP.7xpon_zhRi97l_TudmqkuQHaFj?rs=1&pid=ImgDetMain");
        dish10.setRestaurant(restaurant1);
        dish10.setDishType(dishType);

        Dish dish11 = new Dish();
        dish11.setName("Pad Thai");
        dish11.setDescription("Fideos tailandeses salteados con camarones, tofu, cacahuates y una salsa de tamarindo.");
        dish11.setNormalPrice(new BigDecimal("120.00"));
        dish11.setOfferPrice(new BigDecimal("110.00"));
        dish11.setImagePath("https://th.bing.com/th/id/R.59b845edf85592ecfb9dfde82a142a45?rik=le1dkLiVEDzx2g&riu=http%3a%2f%2fmedia.blogto.com%2farticles%2f20170211-khaosanroad-07.jpg%3fcmd%3dresize_then_crop%26quality%3d70%26w%3d2048%26height%3d1365&ehk=yWZY24uzJqFmHCRbPkwbdsTWsa2xrLWVfCYfJTNWMHA%3d&risl=&pid=ImgRaw&r=0");
        dish11.setRestaurant(restaurant1);
        dish11.setDishType(dishType);

        Dish dish12 = new Dish();
        dish12.setName("Cheesecake");
        dish12.setDescription("Pastel de queso cremoso con base de galleta, cubierto con frutas frescas.");
        dish12.setNormalPrice(new BigDecimal("80.00"));
        dish12.setOfferPrice(new BigDecimal("70.00"));
        dish12.setImagePath("https://th.bing.com/th/id/OIP.FgQBcDcuPAbRDNy_TH2mewHaHa?rs=1&pid=ImgDetMain");
        dish12.setRestaurant(restaurant1);
        dish12.setDishType(dishType);

        Dish dish13 = new Dish();
        dish13.setName("Beef Stroganoff");
        dish13.setDescription("Plato ruso clásico con tiras de carne en una salsa cremosa de champiñones.");
        dish13.setNormalPrice(new BigDecimal("150.00"));
        dish13.setOfferPrice(new BigDecimal("140.00"));
        dish13.setImagePath("https://www.thecountrycook.net/wp-content/uploads/2024/04/thumbnail-Crock-Pot-Beef-Stroganoff-for-Two-scaled.jpg");
        dish13.setRestaurant(restaurant1);
        dish13.setDishType(dishType);

        Dish dish14 = new Dish();
        dish14.setName("Ramen Bowl");
        dish14.setDescription("Ramen japonés con panceta de cerdo, huevo cocido y un caldo sabroso.");
        dish14.setNormalPrice(new BigDecimal("140.00"));
        dish14.setOfferPrice(new BigDecimal("130.00"));
        dish14.setImagePath("https://th.bing.com/th/id/R.aacb60eb30fd5e01e22be07fa939dda9?rik=nFxirW2jW1cb3Q&riu=http%3a%2f%2fcdn.shopify.com%2fs%2ffiles%2f1%2f1291%2f3261%2fcollections%2fRamen_1200x1200.png%3fv%3d1650884026&ehk=uNCsRWh%2fNWU7xiYDXZMugL3wc4d05UzYjyDa7eH6m1g%3d&risl=&pid=ImgRaw&r=0");
        dish14.setRestaurant(restaurant1);
        dish14.setDishType(dishType);

        dishRepository.createDish(dish1);
        dishRepository.createDish(dish2);
        dishRepository.createDish(dish3);
        dishRepository.createDish(dish4);
        dishRepository.createDish(dish5);
        dishRepository.createDish(dish6);
        dishRepository.createDish(dish7);
        dishRepository.createDish(dish8);
        dishRepository.createDish(dish9);
        dishRepository.createDish(dish10);
        dishRepository.createDish(dish11);
        dishRepository.createDish(dish12);
        dishRepository.createDish(dish13);
        dishRepository.createDish(dish14);
        */
    }
}
