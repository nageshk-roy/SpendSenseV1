package com.spendsens.repository;

import com.spendsens.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Page<Transaction> findByUserIdOrderByTimestampDesc(Long userId, Pageable pageable);

    Page<Transaction> findByUserIdAndTimestampBetweenOrderByTimestampDesc(
        Long userId, LocalDateTime start, LocalDateTime end, Pageable pageable);

    Page<Transaction> findByUserIdAndCategoryOrderByTimestampDesc(
        Long userId, TransactionCategory category, Pageable pageable);

    Page<Transaction> findByUserIdAndTypeOrderByTimestampDesc(
        Long userId, TransactionType type, Pageable pageable);

    List<Transaction> findByUserIdAndTimestampBetween(
        Long userId, LocalDateTime start, LocalDateTime end);

    Optional<Transaction> findByDedupKey(String dedupKey);

    boolean existsByDedupKey(String dedupKey);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId " +
           "AND t.type = 'DEBIT' AND YEAR(t.timestamp) = :year AND MONTH(t.timestamp) = :month")
    Double getMonthlyExpense(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user.id = :userId " +
           "AND t.type = 'CREDIT' AND YEAR(t.timestamp) = :year AND MONTH(t.timestamp) = :month")
    Double getMonthlyIncome(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query("SELECT t.category, SUM(t.amount) as total FROM Transaction t " +
           "WHERE t.user.id = :userId AND t.type = 'DEBIT' " +
           "AND YEAR(t.timestamp) = :year AND MONTH(t.timestamp) = :month " +
           "GROUP BY t.category ORDER BY total DESC")
    List<Object[]> getCategoryBreakdown(@Param("userId") Long userId, @Param("year") int year, @Param("month") int month);

    @Query("SELECT t FROM Transaction t WHERE t.user.id = :userId " +
           "AND (LOWER(t.merchant) LIKE %:query% OR LOWER(t.description) LIKE %:query%) " +
           "ORDER BY t.timestamp DESC")
    Page<Transaction> searchTransactions(@Param("userId") Long userId, @Param("query") String query, Pageable pageable);
}
