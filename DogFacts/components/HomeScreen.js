import React from "react";
import { Button, StatusBar, StyleSheet, View, Text } from "react-native";

export class HomeScreen extends React.Component {
  state = {
    randomFactText: "",
    isLoading: true,
  };

  onPressClickMe() {
    this.setState({ randomFactText: "", isLoading: true });

    return fetch("https://dogapi.dog/api/v2/facts")
      .then((res) => res.json())
      .then((res) =>
        this.setState({
          randomFactText: res.data[0].attributes.body,
          isLoading: false,
        })
      )
      .catch((err) => console.error(err));
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.bodyText}>{this.state.randomFactText}</Text>
        <Button
          onPress={() => this.onPressClickMe()}
          title="Press me"
          accessibilityLabel="Click for a random dog fact"
          loading={this.state.isLoading ? true : false}
        />
        <StatusBar style="auto" />
      </View>
    );
  }
}

export default HomeScreen;

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
