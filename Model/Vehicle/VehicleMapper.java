/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Vehicle;

import Model.Mapper.IMapper;

/**
 *
 * @author Johel M
 */
public class VehicleMapper implements IMapper<Vehicle,VehicleDTO>{

    @Override
    public VehicleDTO toDto(Vehicle ent) {
        return new VehicleDTO(
                ent.getLicensePlate(), 
                ent.getBrand(), 
                ent.getModel(), 
                ent.getYear(), 
                ent.getDailyRate(), 
                ent.isAvailable());
    }

    @Override
    public Vehicle toEnt(VehicleDTO dto) {
        return new Vehicle(
                dto.getLicensePlate(), 
                dto.getBrand(), 
                dto.getModel(), 
                dto.getYear(), 
                dto.getDailyRate(), 
                dto.isAvailable());
    }
    
}
