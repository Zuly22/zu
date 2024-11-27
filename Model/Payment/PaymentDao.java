/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Payment;

import DataBase.DataBase;
import Model.Dao.Dao;
import Model.RentalContract.RentalContractDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Johel M
 */
public class PaymentDao extends Dao<PaymentDTO> {

    public PaymentDao(Connection connection) {
        super(connection);
    }

    @Override
    public boolean create(PaymentDTO dto) throws SQLException {
        if (dto == null || !validateFkRentalContract(dto.getRentalContract())) {
            return false;
        }
        String query = "Call PaymentCreate(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, dto.getRentalContract());
            stmt.setDouble(2, dto.getAmount());
            stmt.setString(3, dto.getPaymentMethod());
            stmt.setDate(4, dto.getDate());
            return stmt.executeUpdate() > 0;
        }
    }

    @Override
    public PaymentDTO read(Object id) throws SQLException {
        if (id == null || (int) id == 0) {
            return null;
        }
        String query = "Call PaymentRead(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, (int) id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PaymentDTO(rs.getInt(1),
                            rs.getInt(2),
                            rs.getDouble(3),
                            rs.getString(4),
                            rs.getDate(5)
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<PaymentDTO> readAll() throws SQLException {
        String query = "Call PaymentReadAll()";
        List<PaymentDTO> list = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(new PaymentDTO(rs.getInt(1),
                            rs.getInt(2),
                            rs.getDouble(3),
                            rs.getString(4),
                            rs.getDate(5))
                    );
                }
            }
        }
        return list;
    }

    @Override
    public boolean update(PaymentDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(Object id) throws SQLException {
        if (id == null || (int) id == 0) {
            return false;
        }
        String query = "Call PaymentDelete(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, String.valueOf(id));
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean validateFkRentalContract(Object id) throws SQLException {
        return new RentalContractDao(DataBase.getConnetion()).read(id) != null;
    }


}
