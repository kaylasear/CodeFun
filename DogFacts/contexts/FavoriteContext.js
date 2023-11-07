import { createContext, useState, useContext } from "react";

export const FavoriteContext = createContext();
export const FavoriteContextProvider = FavoriteContext.Provider;
export const useFavoriteContext = () => useContext(FavoriteContext);

export const FavoriteProvider = ({ children }) => {
  const [favorites, setFavorites] = useState([]);

  const addFavorite = (favorite) =>
    setFavorites((prevState) => [...prevState, favorite]);

  const deleteFavorite = (id) =>
    setFavorites((prevState) => {
      return prevState.filter((favorite, index) => {
        return id !== index;
      });
    });

  return (
    <FavoriteContextProvider
      value={{
        favorites,
        addFavorite,
        deleteFavorite,
      }}
    >
      {children}
    </FavoriteContextProvider>
  );
};
