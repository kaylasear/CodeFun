import React, { createContext, useContext } from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import HomeScreen from "../components/HomeScreen";
import AboutScreen from "../components/AboutScreen";
import FavoriteScreen from "../components/FavoriteScreen";
import { FavoriteContext } from "../contexts/FavoriteContext";

export const Tab = createBottomTabNavigator();

function MainTabNavigator() {
  return (
    <Tab.Navigator>
      <Tab.Screen name="Paws Facts" component={HomeScreen} />
      <Tab.Screen name="Favorites" component={FavoriteScreen} />
      <Tab.Screen name="About" component={AboutScreen} />
    </Tab.Navigator>
  );
}

export default MainTabNavigator;
