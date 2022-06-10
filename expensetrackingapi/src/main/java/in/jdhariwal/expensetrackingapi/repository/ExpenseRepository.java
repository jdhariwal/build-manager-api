package in.jdhariwal.expensetrackingapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.jdhariwal.expensetrackingapi.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>{
    
    Page <Expense> findByUserIdAndCategory(Long userId, String category, Pageable page);
  
    Page <Expense> findByUserIdAndNameContaining(Long userId, String keyword, Pageable page);

    Page<Expense> findByUserId(Long userId, Pageable page);

    
}
