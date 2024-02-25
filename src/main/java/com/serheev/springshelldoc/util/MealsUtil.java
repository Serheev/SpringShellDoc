package com.serheev.springshelldoc.util;

import com.serheev.springshelldoc.schema.Meal;
import com.serheev.springshelldoc.schema.Meals;
import com.serheev.springshelldoc.schema.User;
import com.serheev.springshelldoc.schema.UsersWithMeals;
import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@UtilityClass
public class MealsUtil {

    public static void filterAndAddExcess(UsersWithMeals users, Map<String, Object> params) {
        users.getUsers().getUser().forEach(user -> MealsUtil.filterAndAddExcess(user, params));
    }

    public static void filterAndAddExcess(User user, Map<String, Object> params) {
        if (user.getMeals() != null) {
            List<Meal> meals = filterAndAddExcess(user.getMeals().getMeal(), user.getCaloriesPerDay(),
                    (LocalDate) params.get("startDate"), (LocalDate) params.get("endDate"),
                    (LocalTime) params.get("startTime"), (LocalTime) params.get("endTime"));
            user.setMeals(new Meals(meals));
        }
    }

    public static List<Meal> filterAndAddExcess(List<Meal> meals, int caloriesPerDay,
                                                @Nullable LocalDate startDate, @Nullable LocalDate endDate,
                                                @Nullable LocalTime startTime, @Nullable LocalTime endTime) {

        LocalDate adaptStartDate = (startDate == null ? LocalDate.MIN : startDate);
        LocalDate adaptEndDate = (endDate == null || endDate.equals(LocalDate.MAX) ? LocalDate.MAX : endDate.plusDays(1));

        List<Meal> mealsFilteredByDate = meals.stream()
                .filter(meal -> Util.isBetweenHalfOpen(meal.getDate(), adaptStartDate, adaptEndDate)).toList();

        Map<LocalDate, Integer> caloriesSumByDate = mealsFilteredByDate.stream()
                .collect(Collectors.groupingBy(Meal::getDate, Collectors.summingInt(Meal::getCalories)));

        return mealsFilteredByDate.stream()
                .filter(meal -> Util.isBetweenHalfOpen(meal.getTime(), startTime, endTime))
                .peek(meal -> meal.setExcess(caloriesSumByDate.get(meal.getDate()) > caloriesPerDay))
                .toList();
    }
}
