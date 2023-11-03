import React, { useState } from "react";
import {
  Button,
  StatusBar,
  StyleSheet,
  View,
  Text,
  TouchableOpacity,
  Platform,
} from "react-native";
import { Icon } from "react-native-elements";
import FavoriteScreen from "./FavoriteScreen";

export class HomeScreen extends React.Component {
  constructor(props) {
    super(props);
    favorites = new FavoriteScreen();
    this.state = {
      randomFactText: "",
      isFavorite: false,
    };
    this.saveFact = this.saveFact.bind(this);
  }

  static navigationOptions = {
    title: "Paws Facts",
  };

  onPressClickMe() {
    this.setState({ randomFactText: "" });

    return fetch("https://dogapi.dog/api/v2/facts")
      .then((res) => res.json())
      .then((res) =>
        this.setState({
          randomFactText: res.data[0].attributes.body,
          isFavorite: false,
        })
      )
      .catch((err) => console.error(err));
  }

  saveFact(fact) {
    this.setState({ isFavorite: true });
    return favorites.addFavorite(fact);
  }

  removeFact(event) {
    this.setState({ isFavorite: false });
    console.log("removing fact method " + this.state.isFavorite);
  }

  styleHeart() {
    return this.state.isFavorite ? "heart" : "heart-outline";
  }

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.bodyText}>{this.state.randomFactText}</Text>
        <View style={styles.buttonContainer}>
          <Button
            buttonStyle={styles.button}
            onPress={() => this.onPressClickMe()}
            title="Press me"
            accessibilityLabel="Click for a random dog fact"
          />
          <View style={styles.favoriteButton}>
            <TouchableOpacity
              onPress={() =>
                this.state.isFavorite
                  ? this.removeFact(this.state.randomFactText)
                  : this.saveFact(this.state.randomFactText)
              }
            >
              <Icon size={40} name={this.styleHeart()} type="ionicon" />
            </TouchableOpacity>
          </View>
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
  favoriteButton: {
    position: "absolute",
    right: 45,
    bottom: 15,
  },
});
