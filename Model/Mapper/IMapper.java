/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Model.Mapper;

import java.sql.SQLException;

/**
 *
 * @author Johel M
 */
public interface IMapper<Ent,Dto> {
    public Dto toDto(Ent ent);
    public Ent toEnt(Dto dto);
}
