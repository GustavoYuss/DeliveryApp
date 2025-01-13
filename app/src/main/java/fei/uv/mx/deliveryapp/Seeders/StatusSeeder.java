package fei.uv.mx.deliveryapp.Seeders;

import fei.uv.mx.deliveryapp.Models.Status;
import fei.uv.mx.deliveryapp.Repositories.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StatusSeeder implements CommandLineRunner {

    private StatusRepository statusRepository;

    StatusSeeder(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    /**
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {

        Status status = new Status();
        status.setId(1);
        status.setStatus("Procesando");
        Status status2 = new Status();
        status2.setId(1);
        status2.setStatus("enviado");
        Status status3 = new Status();
        status3.setId(1);
        status3.setStatus("completado");
        Status status4 = new Status();
        status4.setId(1);
        status4.setStatus("cancelado");
        statusRepository.save(status);
        statusRepository.save(status2);
        statusRepository.save(status3);
        statusRepository.save(status4);
    }
}