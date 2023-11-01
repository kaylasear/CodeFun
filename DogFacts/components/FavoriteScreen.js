import React from "react";
import { StyleSheet, View, Text } from "react-native";

export class FavoriteScreen extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      favorites: [],
    };
    this.addFavorite = this.addFavorite.bind(this);
  }
  // componentDidMount() {
  //   this.addFavorite = this.addFavorite.bind(this);
  // }

  addFavorite = () => {
    this.setState((prevState) => ({
      favorites: [...prevState.favorites, prevState.fact],
    }));
    //this.setState({ favorites: [...this.state.favorites, this.state.fact] });
    // let { favorites } = this.state;
    // favorites.push(fact);
    // this.setState({ favorites: favorites });
    console.log(this.state.favorites);
  };

  render() {
    if (this.state.favorites.length === 0) {
      return (
        <View style={styles.container}>
          <Text>You have no favorite facts yet</Text>
        </View>
      );
    } else {
      return (
        <View>
          {this.state.favorites.map((factItem, index) => {
            return <Text key={index}>{factItem}</Text>;
          })}
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
