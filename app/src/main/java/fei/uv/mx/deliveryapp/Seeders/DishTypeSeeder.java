package fei.uv.mx.deliveryapp.Seeders;

import fei.uv.mx.deliveryapp.Models.DishType;
import fei.uv.mx.deliveryapp.Repositories.DishTypeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DishTypeSeeder implements CommandLineRunner {

    private DishTypeRepository dishTypeRepository;

    DishTypeSeeder(DishTypeRepository dishTypeRepository) {
        this.dishTypeRepository = dishTypeRepository;
    }

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {


        DishType dishType = new DishType();
        dishType.setId(1);
        dishType.setDishType("Super Mercado");
        dishTypeRepository.createDishType(dishType);

        DishType dishType1 = new DishType();
        dishType1.setId(2);
        dishType1.setDishType("Pizza");
        dishTypeRepository.createDishType(dishType1);

        DishType dishType2 = new DishType();
        dishType2.setId(3);
        dishType2.setDishType("Sushi");
        dishTypeRepository.createDishType(dishType2);

        DishType dishType3 = new DishType();
        dishType3.setId(4);
        dishType3.setDishType("Hamburguesa");
        dishTypeRepository.createDishType(dishType3);

        DishType dishType4 = new DishType();
        dishType4.setId(5);
        dishType4.setDishType("Alitas");
        dishTypeRepository.createDishType(dishType4);

        DishType dishType5 = new DishType();
        dishType5.setId(6);
        dishType5.setDishType("Comida Rapida");
        dishTypeRepository.createDishType(dishType5);

        DishType dishType6 = new DishType();
        dishType6.setId(7);
        dishType6.setDishType("Postres");
        dishTypeRepository.createDishType(dishType6);

        DishType dishType7 = new DishType();
        dishType7.setId(8);
        dishType7.setDishType("Mexicana");
        dishTypeRepository.createDishType(dishType7);

        DishType dishType8 = new DishType();
        dishType8.setId(9);
        dishType8.setDishType("Saludable");
        dishTypeRepository.createDishType(dishType8);

        DishType dishType9 = new DishType();
        dishType9.setId(10);
        dishType9.setDishType("Sándwich");
        dishTypeRepository.createDishType(dishType9);

        DishType dishType10 = new DishType();
        dishType10.setId(11);
        dishType10.setDishType("Italiana");
        dishTypeRepository.createDishType(dishType10);

        DishType dishType11 = new DishType();
        dishType11.setId(12);
        dishType11.setDishType("cafe");
        dishTypeRepository.createDishType(dishType11);

        DishType dishType12 = new DishType();
        dishType12.setId(13);
        dishType12.setDishType("Americana");
        dishTypeRepository.createDishType(dishType12);

        DishType dishType13 = new DishType();
        dishType13.setId(14);
        dishType13.setDishType("Asiática");
        dishTypeRepository.createDishType(dishType13);

        DishType dishType14 = new DishType();
        dishType14.setId(15);
        dishType14.setDishType("Panadería");
        dishTypeRepository.createDishType(dishType14);

        DishType dishType15 = new DishType();
        dishType15.setId(16);
        dishType15.setDishType("Helado");
        dishTypeRepository.createDishType(dishType15);

        DishType dishType16 = new DishType();
        dishType16.setId(17);
        dishType16.setDishType("China");
        dishTypeRepository.createDishType(dishType16);

        DishType dishType17 = new DishType();
        dishType17.setId(18);
        dishType17.setDishType("Sopas");
        dishTypeRepository.createDishType(dishType17);

        DishType dishType18 = new DishType();
        dishType18.setId(19);
        dishType18.setDishType("Vino");
        dishTypeRepository.createDishType(dishType18);

        DishType dishType19 = new DishType();
        dishType19.setId(20);
        dishType19.setDishType("Vegana");
        dishTypeRepository.createDishType(dishType19);


    }
}
