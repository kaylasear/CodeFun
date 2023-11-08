import React from "react";
import { StyleSheet, View, Text, FlatList, Button } from "react-native";
import { useFavoriteContext } from "../contexts/FavoriteContext";
import { Swipeable } from "react-native-gesture-handler";

function FavoriteScreen() {
  const { favorites, deleteFavorite } = useFavoriteContext();

  _renderItem = ({ item, index }, onClick) => {
    const renderRightActions = (progress, dragX, onClick) => {
      return (
        <View style={styles.deleteButton}>
          <Button color="red" onPress={onClick} title="Delete"></Button>
        </View>
      );
    };
    return (
      <Swipeable
        renderRightActions={(progress, dragX) =>
          renderRightActions(progress, dragX, onClick)
        }
      >
        <Text style={styles.bodyText}>{item}</Text>
      </Swipeable>
    );
  };

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
          renderItem={(v) =>
            _renderItem(v, () => {
              deleteFavorite(v.index);
            })
          }
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
  deleteButton: {
    margin: 0,
    alignContent: "center",
    justifyContent: "center",
    width: 70,
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
