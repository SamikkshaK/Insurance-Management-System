package dao;

import entity.Claim;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class ClaimDAOImpl implements IClaimDAO {
    private static final Connection connection = DBConnection.getConnection();

    @Override
    public boolean addClaim(Claim claim) {
        String sql = "INSERT INTO claims (claim_number, date_filed, claim_amount, status, policy_id, client_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, claim.getClaimNumber());
            ps.setDate(2, claim.getDateFiled());
            ps.setDouble(3, claim.getClaimAmount());
            ps.setString(4, claim.getStatus());
            ps.setInt(5, claim.getPolicyId());
            ps.setInt(6, claim.getClientId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in addClaim: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updateClaimStatus(int claimId, String status) {
        String sql = "UPDATE claims SET status = ? WHERE claim_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.setInt(2, claimId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in updateClaimStatus: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Claim getClaimById(int claimId) {
        String sql = "SELECT * FROM claims WHERE claim_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, claimId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Claim(
                        rs.getInt("claim_id"),
                        rs.getString("claim_number"),
                        rs.getDate("date_filed"),
                        rs.getDouble("claim_amount"),
                        rs.getString("status"),
                        rs.getInt("policy_id"),
                        rs.getInt("client_id"));
            }
        } catch (SQLException e) {
            System.out.println("Error in getClaimById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Claim> getAllClaims() {
        List<Claim> claims = new ArrayList<>();
        String sql = "SELECT * FROM claims";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                claims.add(new Claim(
                        rs.getInt("claim_id"),
                        rs.getString("claim_number"),
                        rs.getDate("date_filed"),
                        rs.getDouble("claim_amount"),
                        rs.getString("status"),
                        rs.getInt("policy_id"),
                        rs.getInt("client_id")));
            }
        } catch (SQLException e) {
            System.out.println("Error in getAllClaims: " + e.getMessage());
        }
        return claims;
    }
}
