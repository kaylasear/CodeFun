import React, { useContext, useState } from "react";
import { StyleSheet, View, Text, FlatList } from "react-native";
import { useFavoriteContext } from "../contexts/FavoriteContext";

function FavoriteScreen() {
  const { favorites } = useFavoriteContext();

  if (favorites.length === 0) {
    return (
      <View style={styles.container}>
        <Text>You have no favorite facts yet</Text>
      </View>
    );
  } else {
    console.log("favorites: " + favorites);
    return (
      <View style={styles.container}>
        {favorites?.map((item, id) => (
          <Text key={id}>{item}</Text>
        ))}
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
