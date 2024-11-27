/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Customer;

import Model.Mapper.IMapper;
import Utils.UtilDate;

/**
 *
 * @author Johel M
 */
public class CustomerMapper implements IMapper<Customer,CustomerDTO>{

    @Override
    public CustomerDTO toDto(Customer ent) {
        return new CustomerDTO(
                ent.getId(), 
                ent.getName(), 
                UtilDate.toSqlDate(ent.getBirthDate()), 
                ent.getPhone(), 
                ent.getEmail()
        );
    }

    @Override
    public Customer toEnt(CustomerDTO dto) {
        return new Customer(
                dto.getId(), 
                dto.getName(), 
                UtilDate.toLocalDate(dto.getBirthDate()), 
                dto.getPhone(), 
                dto.getEmail()
        );
    }
    
}
