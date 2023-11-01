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
import FavoriteScreen from "./FavoriteScreen";

export class HomeScreen extends React.Component {
  constructor(props) {
    super(props);
    favorites = new FavoriteScreen();
  }

  static navigationOptions = {
    title: "Paws Facts",
  };
  state = {
    randomFactText: "",
    isLoading: true,
    isFavorite: false,
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

  saveFact(fact) {
    this.setState({ isFavorite: true });
    console.log(this.state.randomFactText, this.state.isFavorite);
    return favorites.addFavorite(fact);
    //return this.props.saveFavorites();
  }

  removeFact(fact) {
    // this.props.removeFact(fact);
    this.setState({ isFavorite: false });
    console.log("removing fact method " + this.state.isFavorite);
    // return this.props.saveFavorites();
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
            loading={this.state.isLoading ? true : false}
          />
          <View style={styles.favoriteButton}>
            <TouchableOpacity
              onPress={() =>
                this.state.isFavorite
                  ? this.removeFact(this.state.randomFactText)
                  : this.saveFact(this.state.randomFactText)
              }
            >
              <Icon size={40} name="heart-outline" type="ionicon" />
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
