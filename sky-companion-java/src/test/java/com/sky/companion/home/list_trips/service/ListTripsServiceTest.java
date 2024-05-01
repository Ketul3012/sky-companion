package com.sky.companion.home.list_trips.service;


import com.sky.companion.common.model.HttpSuccessResponse;
import com.sky.companion.home.common.entity.CompanionProviderTripEntity;
import com.sky.companion.home.list_trips.request.ListTripsRequest;
import com.sky.companion.home.list_trips.response.ListTripsResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test case class for {@link com.sky.companion.home.list_trips.service.ListTripsService} using {@link org.mockito.Mockito}
 */
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListTripsServiceTest {
    final private static List<Integer> languageList = Arrays.asList(1, 2, 3);
    private final static Float maximumCost = 500f;
    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ListTripsServiceImpl listTripsServiceImpl;

    /**
     * This method generate request with empty parameters
     *
     * @return Generated request
     */
    private static ListTripsRequest getEmptyListTripsRequest() {
        ListTripsRequest listTripsRequest = new ListTripsRequest();
        String from = "";
        String to = "";
        String user = "";
        String companionCity = "";
        List<Integer> languageIds = new ArrayList<>();
        listTripsRequest.setTo(to);
        listTripsRequest.setFrom(from);
        listTripsRequest.setUser(user);
        listTripsRequest.setLanguageIds(languageIds);
        listTripsRequest.setMaximumCost(null);
        listTripsRequest.setCompanionCity(companionCity);
        return listTripsRequest;
    }

    /**
     * This method generate request with parameters
     *
     * @return Generated request
     */
    private static ListTripsRequest getFilledListTripRequest() {
        ListTripsRequest listTripsRequest = new ListTripsRequest();

        Date date = Mockito.mock(Date.class);
        String from = "halifax";
        String to = "halifax";
        String user = "me";
        String companionCity = "halifax";
        List<Integer> languageIds = languageList;
        listTripsRequest.setTo(to);
        listTripsRequest.setFrom(from);
        listTripsRequest.setUser(user);
        listTripsRequest.setLanguageIds(languageIds);
        listTripsRequest.setMaximumCost(maximumCost);
        listTripsRequest.setCompanionCity(companionCity);
        listTripsRequest.setDate(date);
        return listTripsRequest;
    }

    /**
     * this method run unit test case for {@link ListTripsService} for success case by checking count and list size of response
     */
    @Test
    void testListTripsSuccessWithRequest() {

        // Arrange
        ListTripsRequest listTripsRequest = getFilledListTripRequest();

        Predicate[] predicate1 = new Predicate[1];

        CriteriaQuery query = Mockito.mock(CriteriaQuery.class);
        Root root = Mockito.mock(Root.class);
        Join userEntityJoin = Mockito.mock(Join.class);
        Join addressEntityJoin = Mockito.mock(Join.class);
        Join languageEntityJoin = Mockito.mock(Join.class);

        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(CompanionProviderTripEntity.class)).thenReturn(query);
        Mockito.when(query.from(CompanionProviderTripEntity.class)).thenReturn(root);

        Mockito.when(query.select(root)).thenReturn(query);

        Mockito.when(root.join("user", JoinType.LEFT)).thenReturn(userEntityJoin);
        Mockito.when(userEntityJoin.join("address", JoinType.LEFT)).thenReturn(addressEntityJoin);
        Mockito.when(userEntityJoin.join("languages", JoinType.LEFT)).thenReturn(languageEntityJoin);

        Predicate predicate = Mockito.mock(Predicate.class);
        Path path = Mockito.mock(Path.class);
        List<Predicate> allPredicates = new ArrayList<>();


        Mockito.when(root.get("source")).thenReturn(path);
        Mockito.when(criteriaBuilder.like(path, "%" + listTripsRequest.getFrom() + "%")).thenReturn(predicate);
        allPredicates.add(predicate);

        Mockito.when(root.get("destination")).thenReturn(path);
        Mockito.when(criteriaBuilder.like(path, "%" + listTripsRequest.getTo() + "%")).thenReturn(predicate);
        allPredicates.add(predicate);


        Mockito.when(root.get("departure")).thenReturn(path);
        Mockito.when(criteriaBuilder.lessThanOrEqualTo(path, listTripsRequest.getDate())).thenReturn(predicate);
        allPredicates.add(predicate);

        Mockito.when(root.get("price")).thenReturn(path);
        Mockito.when(criteriaBuilder.lessThanOrEqualTo(path, listTripsRequest.getMaximumCost())).thenReturn(predicate);
        allPredicates.add(predicate);

        Mockito.when(userEntityJoin.get("firstName")).thenReturn(path);
        Mockito.when(criteriaBuilder.like(path, "%" + listTripsRequest.getUser() + "%")).thenReturn(predicate);

        Mockito.when(userEntityJoin.get("lastName")).thenReturn(path);
        Mockito.when(criteriaBuilder.like(path, "%" + listTripsRequest.getUser() + "%")).thenReturn(predicate);

        Mockito.when(criteriaBuilder.or(Arrays.asList(predicate, predicate).toArray(predicate1))).thenReturn(predicate);
        allPredicates.add(predicate);


        Mockito.when(addressEntityJoin.get("city")).thenReturn(path);
        Mockito.when(criteriaBuilder.like(path, "%" + listTripsRequest.getCompanionCity() + "%")).thenReturn(predicate);
        allPredicates.add(predicate);

        Mockito.when(languageEntityJoin.get("id")).thenReturn(path);
        Mockito.when(path.in(listTripsRequest.getLanguageIds())).thenReturn(predicate);
        allPredicates.add(predicate);

        Mockito.when(criteriaBuilder.and(allPredicates.toArray(predicate1))).thenReturn(predicate);

        Mockito.when(query.where(predicate)).thenReturn(query);

        TypedQuery typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(query)).thenReturn(typedQuery);
        List<CompanionProviderTripEntity> mockTripEntities = new ArrayList<>();
        Mockito.when(typedQuery.getResultList()).thenReturn(mockTripEntities);

        // Act
        HttpSuccessResponse<ListTripsResponse> response = listTripsServiceImpl.listTrips(listTripsRequest);

        // Assert
        assertNotNull(response.getData().getTrips());
    }

    /**
     * this method run unit test case for {@link ListTripsService} for success case by checking count and list size of response
     */
    @Test
    void testListTripsSuccessWithoutRequest() {

        // Arrange
        ListTripsRequest listTripsRequest = new ListTripsRequest();
        CriteriaQuery query = Mockito.mock(CriteriaQuery.class);
        Root root = Mockito.mock(Root.class);
        Join userEntityJoin = Mockito.mock(Join.class);
        Join addressEntityJoin = Mockito.mock(Join.class);
        Join languageEntityJoin = Mockito.mock(Join.class);

        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(CompanionProviderTripEntity.class)).thenReturn(query);
        Mockito.when(query.from(CompanionProviderTripEntity.class)).thenReturn(root);

        Mockito.when(root.join("user", JoinType.LEFT)).thenReturn(userEntityJoin);
        Mockito.when(userEntityJoin.join("address", JoinType.LEFT)).thenReturn(addressEntityJoin);
        Mockito.when(userEntityJoin.join("languages", JoinType.LEFT)).thenReturn(languageEntityJoin);

        TypedQuery typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(query)).thenReturn(typedQuery);
        List<CompanionProviderTripEntity> mockTripEntities = new ArrayList<>();
        Mockito.when(typedQuery.getResultList()).thenReturn(mockTripEntities);

        // Act
        HttpSuccessResponse<ListTripsResponse> response = listTripsServiceImpl.listTrips(listTripsRequest);

        // Assert
        assertNotNull(response.getData().getTrips());
    }

    /**
     * this method run unit test case for {@link ListTripsService} for success case by checking count and list size of response
     */
    @Test
    void testListTripsSuccessWithEmptyRequest() {

        // Arrange
        ListTripsRequest listTripsRequest = getEmptyListTripsRequest();

        CriteriaQuery query = Mockito.mock(CriteriaQuery.class);
        Root root = Mockito.mock(Root.class);
        Join userEntityJoin = Mockito.mock(Join.class);
        Join addressEntityJoin = Mockito.mock(Join.class);
        Join languageEntityJoin = Mockito.mock(Join.class);

        CriteriaBuilder criteriaBuilder = Mockito.mock(CriteriaBuilder.class);

        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        Mockito.when(criteriaBuilder.createQuery(CompanionProviderTripEntity.class)).thenReturn(query);
        Mockito.when(query.from(CompanionProviderTripEntity.class)).thenReturn(root);

        Mockito.when(root.join("user", JoinType.LEFT)).thenReturn(userEntityJoin);
        Mockito.when(userEntityJoin.join("address", JoinType.LEFT)).thenReturn(addressEntityJoin);
        Mockito.when(userEntityJoin.join("languages", JoinType.LEFT)).thenReturn(languageEntityJoin);

        TypedQuery typedQuery = Mockito.mock(TypedQuery.class);
        Mockito.when(entityManager.createQuery(query)).thenReturn(typedQuery);
        List<CompanionProviderTripEntity> mockTripEntities = new ArrayList<>();
        Mockito.when(typedQuery.getResultList()).thenReturn(mockTripEntities);

        // Act
        HttpSuccessResponse<ListTripsResponse> response = listTripsServiceImpl.listTrips(listTripsRequest);

        // Assert
        assertNotNull(response.getData().getTrips());
    }
}
