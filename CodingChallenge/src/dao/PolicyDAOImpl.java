package dao;

import entity.Policy;
import util.DBConnection;

import java.sql.*;
import java.util.*;

public class PolicyDAOImpl implements IPolicyDAO {
    private static final Connection connection = DBConnection.getConnection();

    @Override
    public boolean addPolicy(Policy policy) {
        String sql = "INSERT INTO policies (policy_name, coverage_amount, premium) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, policy.getPolicyName());
            ps.setDouble(2, policy.getCoverageAmount());
            ps.setDouble(3, policy.getPremium());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in addPolicy: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        String sql = "UPDATE policies SET policy_name = ?, coverage_amount = ?, premium = ? WHERE policy_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, policy.getPolicyName());
            ps.setDouble(2, policy.getCoverageAmount());
            ps.setDouble(3, policy.getPremium());
            ps.setInt(4, policy.getPolicyId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in updatePolicy: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        String sql = "DELETE FROM policies WHERE policy_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, policyId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error in deletePolicy: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Policy getPolicyById(int policyId) {
        String sql = "SELECT * FROM policies WHERE policy_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, policyId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Policy(
                        rs.getInt("policy_id"),
                        rs.getString("policy_name"),
                        rs.getDouble("coverage_amount"),
                        rs.getDouble("premium"));
            }
        } catch (SQLException e) {
            System.out.println("Error in getPolicyById: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> policies = new ArrayList<>();
        String sql = "SELECT * FROM policies";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                policies.add(new Policy(
                        rs.getInt("policy_id"),
                        rs.getString("policy_name"),
                        rs.getDouble("coverage_amount"),
                        rs.getDouble("premium")));
            }
        } catch (SQLException e) {
            System.out.println("Error in getAllPolicies: " + e.getMessage());
        }
        return policies;
    }
}
