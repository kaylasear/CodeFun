import { StatusBar } from "expo-status-bar";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import React, { useState } from "react";
import { Alert, StyleSheet, Text, View, Button } from "react-native";
import Header from "./components/Header";

const Stack = createNativeStackNavigator();
function App() {
  const [randomFactText, setRandomFact] = useState("");

  function HomeScreen() {
    return (
      <View style={styles.container}>
        <Text style={styles.bodyText}>{randomFactText}</Text>
        <Button
          onPress={onPressClickMe}
          title="Press me"
          accessibilityLabel="Click for a random dog fact"
        />
        <StatusBar style="auto" />
      </View>
    );
  }

  function onPressClickMe(event) {
    var randomFact = "I am a random fact";
    setRandomFact(randomFact);
  }
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen
          name="Dog Facts App"
          component={HomeScreen}
          options={{
            headerStyle: {
              backgroundColor: "#008080",
            },
            headerTitleStyle: {
              fontSize: 25,
            },
          }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center",
  },
  titleText: {
    padding: 10,
    fontSize: 70,
  },
  bodyText: {
    fontSize: 20,
  },
});

export default App;
