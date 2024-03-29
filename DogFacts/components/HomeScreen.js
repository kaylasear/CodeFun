import React, { useState } from "react";
import {
  Button,
  StatusBar,
  StyleSheet,
  View,
  Text,
  TouchableOpacity,
} from "react-native";
import { Icon } from "react-native-elements";
import { useFavoriteContext } from "../contexts/FavoriteContext";

function HomeScreen() {
  const { favorites, addFavorite, deleteFavorite } = useFavoriteContext();
  const [state, setState] = useState({
    isFavorite: false,
    randomFactText: "",
  });

  function onPressClickMe() {
    setState({ randomFactText: "" });

    return fetch("https://dogapi.dog/api/v2/facts")
      .then((res) => res.json())
      .then((res) =>
        setState({
          randomFactText: res.data[0].attributes.body,
          isFavorite: false,
        })
      )
      .catch((err) => console.error(err));
  }

  function saveFact(fact) {
    setState({ randomFactText: fact, isFavorite: true });
    addFavorite(fact);
  }

  function removeFact(fact) {
    let factId = favorites.findIndex((fav) => fav === fact);
    if (factId) {
      deleteFavorite(factId);
    }
    setState({ randomFactText: fact, isFavorite: false });
  }

  function styleHeart() {
    return state.isFavorite ? "heart" : "heart-outline";
  }

  return (
    <View style={styles.container}>
      <Text style={styles.bodyText}>{state.randomFactText}</Text>
      <View style={styles.buttonContainer}>
        <Button
          buttonStyle={styles.button}
          onPress={onPressClickMe}
          title="Press me"
          accessibilityLabel="Click for a random dog fact"
        />
        <View style={styles.favoriteButton}>
          <TouchableOpacity
            onPress={() =>
              state.isFavorite
                ? removeFact(state.randomFactText)
                : saveFact(state.randomFactText)
            }
          >
            <Icon size={40} name={styleHeart()} type="ionicon" />
          </TouchableOpacity>
        </View>
      </View>
      <StatusBar style="auto" />
    </View>
  );
}

export default HomeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#FFF",
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
