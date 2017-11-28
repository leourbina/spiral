(ns spiral.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [cheshire.core :as json]))

(defn peel-level
  [matrix level]
  (let [max-x (count matrix)
        max-y (count (get matrix 0))
        rows (subvec matrix level (max (- max-x level) 1))]
    (vec (map (fn [row] (subvec row level (- max-y level))) rows))))

(defn square-indices
  "Returns the indices resulting from going around in a square,
  along with the remaining inner matrix"
  [matrix level]
  (let [max-x (count matrix)
        max-y (count (get matrix 0))
        outer-indices (into [] (map
                                 (fn [entry] (map + entry [level level]))
                                 (concat
                                   (map vector (repeat 0) (range 0 max-x))
                                   (map vector (range 1 max-y) (repeat (- max-x 1)))
                                   (map vector (repeat (- max-y 1)) (range (- max-x 2) -1 -1))
                                   (map vector (range (- max-y 2) 0 -1) (repeat 0)))))
        rest (peel-level matrix 1)]
    [outer-indices rest]))

(defn spiral-indices
  "Returns an array of the indices that result from traversing a matrix in spiral order"
  [matrix]
  (loop [matrix matrix
         indices []
         level 0]
    (if (empty? matrix)
      indices
      (let [[outer-indices rest] (square-indices matrix level)]
        (recur rest (concat indices outer-indices) (inc level))))))

(defn spiral-traverse
  [matrix]
  (map #(get-in matrix %) (spiral-indices matrix)))

(defn -main
  [file-path]
  (let [matrix (-> (io/reader file-path)
                 (json/parse-stream true)
                 vec)]
    (prn (json/generate-string (spiral-traverse matrix)))))
