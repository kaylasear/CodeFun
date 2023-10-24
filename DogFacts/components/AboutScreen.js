import React from "react";
import { StyleSheet, View, Text } from "react-native";

export class AboutScreen extends React.Component {
  current_year = new Date().getFullYear;

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.bodyText}>
          Paws Facts is my first React Native mobile app. I own the sweetest and
          ditziest Golden Retriever and so I wanted to create something about
          dogs! Use this to show off all your friends how cool you are with
          these interesting facts about a man's bestfriend.
        </Text>
        <Text>Copyright ⓒ Kayla Sear {this.current_year}</Text>
      </View>
    );
  }
}

export default AboutScreen;

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
