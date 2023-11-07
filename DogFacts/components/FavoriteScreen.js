import React, { useContext, useState } from "react";
import { StyleSheet, View, Text, FlatList } from "react-native";
import { useFavoriteContext } from "../contexts/FavoriteContext";
import { ScrollView } from "react-native-gesture-handler";

function FavoriteScreen() {
  const { favorites } = useFavoriteContext();

  _renderItem = ({ item }) => <Text style={styles.bodyText}>{item}</Text>;

  const myItemSeparator = () => {
    return (
      <View
        style={{ height: 1, backgroundColor: "grey", marginHorizontal: 10 }}
      />
    );
  };

  if (favorites.length === 0) {
    return (
      <View style={styles.container}>
        <Text>You have no favorite facts yet</Text>
      </View>
    );
  } else {
    return (
      <View style={styles.container}>
        <FlatList
          data={favorites}
          renderItem={this._renderItem}
          ItemSeparatorComponent={myItemSeparator}
        />
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
    fontSize: 25,
    textAlign: "center",
    padding: 25,
  },
});
