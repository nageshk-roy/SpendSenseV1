package com.spendsens.repository;

import com.spendsens.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByUserIdAndMonthAndYear(Long userId, int month, int year);
    Optional<Budget> findByUserIdAndCategoryAndMonthAndYear(Long userId, TransactionCategory category, int month, int year);

    @Modifying
    @Query("UPDATE Budget b SET b.currentSpent = b.currentSpent + :amount " +
           "WHERE b.user.id = :userId AND b.category = :category AND b.month = :month AND b.year = :year")
    int incrementSpent(@Param("userId") Long userId, @Param("category") TransactionCategory category,
                      @Param("amount") Double amount, @Param("month") int month, @Param("year") int year);
}
