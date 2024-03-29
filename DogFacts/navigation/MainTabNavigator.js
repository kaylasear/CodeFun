import React from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import HomeScreen from "../components/HomeScreen";
import AboutScreen from "../components/AboutScreen";
import FavoriteScreen from "../components/FavoriteScreen";
import { Ionicons, FontAwesome5 } from "@expo/vector-icons";

export const Tab = createBottomTabNavigator();

function MainTabNavigator() {
  return (
    <Tab.Navigator>
      <Tab.Screen
        name="Paws Facts"
        component={HomeScreen}
        options={{
          tabBarIcon: ({ color, size }) => (
            <FontAwesome5 name="dog" color={color} size={size} />
          ),
          headerStyle: {
            backgroundColor: "#D2E0FB",
          },
          headerTitleStyle: {
            fontSize: 25,
          },
          tabBarStyle: {
            backgroundColor: "#D2E0FB",
          },
        }}
      />
      <Tab.Screen
        name="Favorites"
        component={FavoriteScreen}
        options={{
          tabBarIcon: ({ color, size }) => (
            <FontAwesome5 name="list" color={color} size={size} />
          ),
          tabBarStyle: {
            backgroundColor: "#D2E0FB",
          },
          headerStyle: {
            backgroundColor: "#D2E0FB",
          },
          headerTitleStyle: {
            fontSize: 25,
          },
        }}
      />
      <Tab.Screen
        name="About"
        component={AboutScreen}
        options={{
          tabBarIcon: ({ color, size }) => (
            <Ionicons
              name="information-circle-sharp"
              color={color}
              size={size}
            />
          ),
          tabBarStyle: {
            backgroundColor: "#D2E0FB",
          },
          headerStyle: {
            backgroundColor: "#D2E0FB",
          },
          headerTitleStyle: {
            fontSize: 25,
          },
        }}
      />
    </Tab.Navigator>
  );
}

export default MainTabNavigator;
