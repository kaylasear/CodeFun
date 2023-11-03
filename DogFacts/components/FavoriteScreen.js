import React, { useState } from "react";
import { StyleSheet, View, Text, FlatList } from "react-native";

export class FavoriteScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      favorites: [],
    };
    this.addFavorite = this.addFavorite.bind(this);
    this.getFavorites = this.getFavorites.bind(this);
  }

  componentDidMount() {
    const favs = this.getFavorites();
    this.setState({ favorites: favs });
  }

  getFavorites() {
    return this.state.favorites;
  }

  addFavorite(fact) {
    let favs = this.getFavorites();
    favs.push(fact);
    this.setState({ favorites: favs });
  }
  render() {
    if (this.state.favorites.length === 0) {
      return (
        <View style={styles.container}>
          <Text>You have no favorite facts yet</Text>
        </View>
      );
    } else {
      const favs = this.getFavorites();
      favs.map((fav, i) => ({
        key: `${i}`,
        text: fav,
      }));
      console.log(favs);
      return (
        <View style={styles.container}>
          <FlatList data={favs} numColumns={3} />
        </View>
      );
    }
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
