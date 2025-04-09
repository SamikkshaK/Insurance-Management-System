package dao;

import entity.Policy;
import java.util.List;

public interface IPolicyDAO {
    boolean addPolicy(Policy policy);
    boolean updatePolicy(Policy policy);
    boolean deletePolicy(int policyId);
    Policy getPolicyById(int policyId);
    List<Policy> getAllPolicies();
}
