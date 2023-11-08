import { NavigationContainer } from "@react-navigation/native";
import React from "react";
import { StyleSheet, View } from "react-native";
import { FavoriteProvider } from "./contexts/FavoriteContext";
import MainTabNavigator from "./navigation/MainTabNavigator";
import { GestureHandlerRootView } from "react-native-gesture-handler";

export default function App() {
  return (
    <GestureHandlerRootView style={{ flex: 1 }}>
      <View style={styles.container}>
        <FavoriteProvider>
          <NavigationContainer>
            <MainTabNavigator />
          </NavigationContainer>
        </FavoriteProvider>
      </View>
    </GestureHandlerRootView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
});
