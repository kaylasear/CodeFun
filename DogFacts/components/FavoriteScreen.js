import React from "react";
import { StyleSheet, View, Text } from "react-native";

export class FavoriteScreen extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Text>My favorite facts</Text>
      </View>
    );
  }
}

export default FavoriteScreen;

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
    textAlign: "center",
  },
});
