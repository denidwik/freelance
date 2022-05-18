package com.project.freelance.gateway.impl;

import com.project.freelance.entity.User;
import com.project.freelance.gateway.UserJPAGateway;
import com.project.freelance.model.UserEntity;
import com.project.freelance.model.UserEntity_;
import com.project.freelance.repository.UserRepository;
import com.project.freelance.util.IntegerUtils;
import com.project.freelance.util.Paginated;
import com.project.freelance.util.StringUtils;
import com.project.freelance.dto.request.UserListFilterRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.project.freelance.util.Constant.DEFAULT_PAGE_NO;
import static com.project.freelance.util.Constant.DEFAULT_PAGE_SIZE;

@AllArgsConstructor
public class UserJPAGatewayImpl implements UserJPAGateway {

    private final UserRepository userRepository;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public User findByUserId(long userId) {
        return userRepository.findById(userId).map(this::mapPhysicalToLogicalEntity).orElse(null);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public boolean existsByUserName(String userName) {
        return userRepository.existsByUserName(userName);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public User findUserByEmailAndNotUserId(String email, Long userId) {
        return userRepository.findByEmailAndUserIdIsNot(email, userId).map(this::mapPhysicalToLogicalEntity).orElse(null);
    }

    @Override
    public Long save(User user, long userId) {
        UserEntity entity = mapLogicalToPhysicalEntity(user);
        entity = userRepository.save(entity);
        return entity.getUserId();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Paginated<User> findAllByFilter(UserListFilterRequest filter) {
        int pageNo = IntegerUtils.isNullOrZeroOrNegative(filter.getPageNo()) ? DEFAULT_PAGE_NO : filter.getPageNo() - 1;
        int pageSize = IntegerUtils.isNullOrZeroOrNegative(filter.getPageSize()) ? DEFAULT_PAGE_SIZE : filter.getPageSize();
        List<Sort.Order> orders = constructSorting(filter);
        Pageable pagination = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        Page<UserEntity> userListPage = userRepository.findAll(getQuerySpecification(filter), pagination);
        if (userListPage == null || userListPage.getContent().isEmpty()) {
            return new Paginated<>();
        } else {
            Paginated<User> paginated = new Paginated<>();
            paginated.setResults(userListPage.stream().map(this::mapPhysicalToLogicalEntity).collect(Collectors.toList()));
            paginated.setTotalPage(userListPage.getTotalPages());
            paginated.setTotalRecord(userListPage.getTotalElements());
            return paginated;
        }
    }

    private Specification<UserEntity> getQuerySpecification(UserListFilterRequest filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.isNotNullAndNotEmpty(filter.getUserNameContain()))
                predicates.add(criteriaBuilder.like(root.get(UserEntity_.USER_NAME), "%" + filter.getUserNameContain() + "%"));
            if (StringUtils.isNotNullAndNotEmpty(filter.getUserName()))
                predicates.add(criteriaBuilder.like(root.get(UserEntity_.USER_NAME), filter.getUserName()));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private List<Sort.Order> constructSorting(UserListFilterRequest filter) {
        List<Sort.Order> orders = new ArrayList<>();
        if(StringUtils.isNotNullAndNotEmpty(filter.getSort())){
            for (int properties = 0; properties <= filter.getSort().split(",").length; properties++) {
                if (((filter.getSort().split(",")[properties]).split(" ")[1]).equals("ASC")){
                    orders.add(new Sort.Order(Sort.Direction.ASC, (filter.getSort().split(",")[properties]).split(" ")[0]));
                }
                else if (((filter.getSort().split(",")[properties]).split(" ")[1]).equals("DESC")){
                    orders.add(new Sort.Order(Sort.Direction.DESC, (filter.getSort().split(",")[properties]).split(" ")[0]));
                }
            }
        }

        return orders;
    }

    private UserEntity mapLogicalToPhysicalEntity(User user) {
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(user, entity);
        return entity;
    }

    private User mapPhysicalToLogicalEntity(UserEntity entity){
        User user = new User();
        BeanUtils.copyProperties(entity, user);
        return user;
    }

}
