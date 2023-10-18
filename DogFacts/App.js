import { StatusBar } from "expo-status-bar";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React, { useState } from "react";
import { Alert, StyleSheet, Text, View, Button } from "react-native";
import Header from "./components/Header";
import { AppLoading, Asset, Font, Icon } from "expo";
import { HomeScreen } from "./components/HomeScreen";

const Stack = createNativeStackNavigator();

export default class App extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <NavigationContainer>
          <Stack.Navigator>
            <Stack.Screen
              name="Dog Facts"
              component={HomeScreen}
              options={{
                headerStyle: {
                  backgroundColor: "#b2d8d8",
                },
                headerTitleStyle: {
                  fontSize: 25,
                },
              }}
            />
          </Stack.Navigator>
        </NavigationContainer>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
  },
});
