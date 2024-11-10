package fei.uv.mx.deliveryapp.Repositories;

import fei.uv.mx.deliveryapp.Models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    default Address createAddress(Address address) {
        return save(address);
    }

    default Address getAddress(int id) {
        return findById(id).orElse(null);
    }

    default boolean deleteAddress(int id) {
        if (existsById(id)) {
            deleteById(id);
            return true;
        }
        return false;
    }

    default boolean updateAddress(Address address) {
        if (existsById(address.getId())) {
            save(address);
            return true;
        }
        return false;
    }

    @Query("SELECT u FROM Address u")
    List<Address> getAllAddress();

}
