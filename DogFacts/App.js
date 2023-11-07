import { NavigationContainer } from "@react-navigation/native";
import React from "react";
import { StyleSheet, View } from "react-native";
import { FavoriteProvider } from "./contexts/FavoriteContext";
import MainTabNavigator from "./navigation/MainTabNavigator";

export default function App() {
  return (
    <View style={styles.container}>
      <FavoriteProvider>
        <NavigationContainer>
          <MainTabNavigator />
        </NavigationContainer>
      </FavoriteProvider>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
});
