/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.RentalContract;

import DataBase.DataBase;
import Model.Customer.CustomerDao;
import Model.Customer.CustomerMapper;
import Model.Mapper.IMapper;
import Model.Vehicle.VehicleDao;
import Model.Vehicle.VehicleMapper;
import Utils.UtilDate;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Johel M
 */
public class RentalContractMapper implements IMapper<RentalContract,RentalContractDTO>{

    @Override
    public RentalContractDTO toDto(RentalContract ent) {
        return new RentalContractDTO(
                ent.getId(),
                ent.getCustomer().getId(),
                ent.getVehicle().getLicensePlate(),
                ent.getVehicle().getDailyRate(),
                UtilDate.toSqlDate(ent.getStartDate()),
                UtilDate.toSqlDate(ent.getEndDate())
        );
    }

    @Override
    public RentalContract toEnt(RentalContractDTO dto){
        try {
            return new RentalContract(
                    dto.getId(),
                    new CustomerMapper().toEnt(new CustomerDao(DataBase.getConnetion()).read(dto.getCustomer())),
                    new VehicleMapper().toEnt(new VehicleDao(DataBase.getConnetion()).read(dto.getVehicle())),
                    UtilDate.toLocalDate(dto.getStartDate()),
                    UtilDate.toLocalDate(dto.getEndDate())
            );
        } catch (SQLException ex) {
            Logger.getLogger(RentalContractMapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
