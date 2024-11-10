package fei.uv.mx.deliveryapp.Services.interfaces;

import fei.uv.mx.deliveryapp.Models.Address;
import fei.uv.mx.deliveryapp.Models.City;
import fei.uv.mx.deliveryapp.Models.State;

import java.util.List;

public interface ILocationServices {
    public City createCity(City city);
    public City getCity(int id);
    public boolean deleteCity(int id);
    public boolean updateCity(City city);
    public List<City> getAllCities();

    public State createState(State state);
    public State getState(int id);
    public boolean deleteState(int id);
    public boolean updateState(State state);
    public List<State> getAllStates();

    public Address createAddress(Address address);
    public Address getAddress(int id);
    public boolean deleteAddress(int id);
    public boolean updateAddress(Address address);
    public List<Address> getAllAddress();
}
