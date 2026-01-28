package com.uo.price_comparator.repository;

import com.uo.price_comparator.dto.DiscountDto;
import com.uo.price_comparator.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    @Query("""
    select d from Discount d
    where d.product.id = :productId
      and d.supermarket.id = :supermarketId
      and :date between d.fromDate and d.toDate
    order by d.percentageOfDiscount desc, d.toDate asc
""")
    List<Discount> findActiveDiscountsOrdered(@Param("productId") Long productId,
                                              @Param("supermarketId") Long supermarketId,
                                              @Param("date") LocalDate date);

    @Query("""
    select new com.uo.price_comparator.dto.DiscountDto(
                  d.id,
                  p.name,
                  p.brand,
                  p.imageUrl,
                  s.name,
                  pp.price,
                  (pp.price * (100 - d.percentageOfDiscount) / 100),
                  d.percentageOfDiscount,
                  d.fromDate,
                  d.toDate,
                  p.quantity,
                  p.unit,
                  null,
                  null,
                  null
              )
    from Discount d
    join d.product p
    join d.supermarket s
    join ProductPrice pp
      on pp.product = d.product
     and pp.supermarket = d.supermarket
     and pp.priceDate = (
        select max(pp2.priceDate)
        from ProductPrice pp2
        where pp2.product = d.product
         and pp2.supermarket = d.supermarket
         and pp2.priceDate <= :today
     )
    where :today between d.fromDate and d.toDate
    order by d.percentageOfDiscount desc, d.toDate asc
   """)
    List<DiscountDto> findDiscountsForToday(@Param("today") LocalDate today);

    @Query("""
        select new com.uo.price_comparator.dto.DiscountDto(
                     d.id,
                     p.name,
                     p.brand,
                     p.imageUrl,
                     s.name,
                     pp.price,
                     (pp.price * (100 - d.percentageOfDiscount) / 100),
                     d.percentageOfDiscount,
                     d.fromDate,
                     d.toDate,
                     p.quantity,
                     p.unit,
                     null,
                     null,
                     null
                 )
        from Discount d
        join d.product p
        join d.supermarket s
        join ProductPrice pp
          on pp.product = d.product
         and pp.supermarket = d.supermarket
         and pp.priceDate = (
            select max(pp2.priceDate)
            from ProductPrice pp2
            where pp2.product = d.product
             and pp2.supermarket = d.supermarket
             and pp2.priceDate <= :today
         )
        where d.fromDate <= :endOfWeek
          and d.toDate >= :startOfWeek
          and d.toDate >= :today
        order by d.percentageOfDiscount desc, d.toDate asc
    """)
    List<DiscountDto> findDiscountsForThisWeek(@Param("startOfWeek") LocalDate startOfWeek,
                                               @Param("endOfWeek") LocalDate endOfWeek,
                                               @Param("today") LocalDate today);
}
