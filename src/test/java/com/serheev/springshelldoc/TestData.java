package com.serheev.springshelldoc;

import com.serheev.springshelldoc.schema.Meal;
import com.serheev.springshelldoc.schema.Meals;
import com.serheev.springshelldoc.schema.RoleTypes;
import com.serheev.springshelldoc.schema.User;
import com.serheev.springshelldoc.util.Util;

import java.io.File;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.of;

public class TestData {
    public static final File inputFile = new File("in/usersWithMeals.xml");

    public static final Map<String, Object> paramsMap =
            Util.parseParams(List.of("startDate=2024-01-30", "endDate=2024-01-30", "startTime=11:00", "endTime=19:00"));

    public static final Date registered = new Date(124, Calendar.JANUARY, 1, 13, 0, 0);

    public static final Meal userLunch = new Meal("Lunch", 1000, of(2024, Month.JANUARY, 30, 13, 0, 0), false);
    public static final Meals userMeals = new Meals(List.of(
            new Meal("Breakfast", 500, of(2024, Month.JANUARY, 30, 10, 0, 0), false),
            userLunch,
            new Meal("Dinner", 500, of(2024, Month.JANUARY, 30, 20, 0, 0), false),
            new Meal("Food to the limit", 100, of(2024, Month.JANUARY, 31, 0, 0, 0), true),
            new Meal("Breakfast", 500, of(2024, Month.JANUARY, 31, 10, 0, 0), true),
            new Meal("Lunch", 1000, of(2024, Month.JANUARY, 31, 13, 0, 0), true),
            new Meal("Dinner", 510, of(2024, Month.JANUARY, 31, 20, 0, 0), true)
    ));
    public static final User user = new User("User", "user@serheev.com", "password", 2005, registered, true,
            List.of(RoleTypes.USER), null);

    public static final Meal adminLunch = new Meal("Admin Lunch", 510, of(2024, Month.JANUARY, 30, 14, 0, 0), false);
    public static final Meals adminMeals = new Meals(List.of(
            adminLunch,
            new Meal("Admin Dinner", 730, of(2024, Month.JANUARY, 30, 21, 0, 0), false)
    ));
    public static final User admin = new User("Admin", "admin@serheev.com", "admin", 1900, registered, true,
            List.of(RoleTypes.ADMIN, RoleTypes.USER), null);

    public static final User guest = new User("Guest", "guest@serheev.com", "guest", 2000, registered, false,
            List.of(), null);

    public static List<User> getUsers() {
        user.setMeals(userMeals);
        admin.setMeals(adminMeals);
        return List.of(user, admin, guest);
    }

    public static List<User> getFilteredUsers() {
        user.setMeals(new Meals(List.of(userLunch)));
        admin.setMeals(new Meals(List.of(adminLunch)));
        return List.of(user, admin, guest);
    }
}
