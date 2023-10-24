import React from "react";
import {
  Button,
  StatusBar,
  StyleSheet,
  View,
  Text,
  TouchableOpacity,
} from "react-native";
import { Icon } from "react-native-elements";

export class HomeScreen extends React.Component {
  static navigationOptions = {
    title: "Paws Facts",
  };
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
        <View style={styles.buttonContainer}>
          <TouchableOpacity>
            <Icon />
          </TouchableOpacity>
          <Button
            buttonStyle={styles.button}
            onPress={() => this.onPressClickMe()}
            title="Press me"
            accessibilityLabel="Click for a random dog fact"
            loading={this.state.isLoading ? true : false}
          />
        </View>
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
  button: {
    height: 50,
    width: 200,
  },
  buttonContainer: {
    alignItems: "flex-end",
    bottom: 0,
    flexDirection: "row",
    justifyContent: "center",
    left: 0,
    padding: 20,
    position: "absolute",
    right: 0,
    top: 0,
  },
});
