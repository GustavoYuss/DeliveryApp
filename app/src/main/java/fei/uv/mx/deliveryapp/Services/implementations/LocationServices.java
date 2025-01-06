package fei.uv.mx.deliveryapp.Services.implementations;

import fei.uv.mx.deliveryapp.Models.Address;
import fei.uv.mx.deliveryapp.Models.City;
import fei.uv.mx.deliveryapp.Models.State;
import fei.uv.mx.deliveryapp.Repositories.AddressRepository;
import fei.uv.mx.deliveryapp.Repositories.CityRepository;
import fei.uv.mx.deliveryapp.Repositories.StateRepository;
import fei.uv.mx.deliveryapp.Services.interfaces.ILocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServices implements ILocationServices {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    AddressRepository addressRepository;
    /**
     * @param city
     * @return
     */
    @Override
    public City createCity(City city) {
        return cityRepository.createCity(city);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public City getCity(int id) {
        return cityRepository.getCity(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteCity(int id) {
        return cityRepository.deleteCity(id);
    }

    /**
     * @param city
     * @return
     */
    @Override
    public boolean updateCity(City city) {
        return cityRepository.updateCity(city);
    }

    /**
     * @return
     */
    @Override
    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }

    /**
     * @param state
     * @return
     */
    @Override
    public State createState(State state) {
        return stateRepository.createState(state);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public State getState(int id) {
        return stateRepository.getState(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteState(int id) {
        return stateRepository.deleteState(id);
    }

    /**
     * @param state
     * @return
     */
    @Override
    public boolean updateState(State state) {
        return stateRepository.updateState(state);
    }

    /**
     * @return
     */
    @Override
    public List<State> getAllStates() {
        return stateRepository.getAllStates();
    }

    /**
     * @param address
     * @return
     */
    @Override
    public Address createAddress(Address address) {
        return addressRepository.createAddress(address);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Address getAddress(int id) {
        return addressRepository.getAddress(id);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean deleteAddress(int id) {
        return addressRepository.deleteAddress(id);
    }

    /**
     * @param address
     * @return
     */
    @Override
    public boolean updateAddress(Address address) {
        return addressRepository.updateAddress(address);
    }

    /**
     * @return
     */
    @Override
    public List<Address> getAllAddress() {
        return addressRepository.getAllAddress();
    }

    @Override
    public int getAddressByUser(int idUser) {
        return addressRepository.getAddressByUser(idUser);
    }
}
