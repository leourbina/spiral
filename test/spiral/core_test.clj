(ns spiral.core-test
  (:require [spiral.core :refer :all]
            [midje.sweet :refer :all]))

(fact "About peeling matrices"
  (fact "Can peel 1x1"
    (peel-level [[1]] 1) => [])

  (fact "Can peel 2x2"
    (peel-level [[1 2]
                 [3 4]] 1) => [])

  (fact "Can peel 3x3"
    (peel-level [[1 2 3]
                 [4 5 6]
                 [7 8 9]] 1) => [[5]]))

(facts "About spiral-traversing matrices"
  (fact "Can traverse 1x1 matrix"
    (spiral-indices [[1]]))

  (fact "Can traverse 3x3 matrix"
    (spiral-indices [[1, 2, 3],
                     [0, 1, 2],
                     [9, 1, 3]]) => [[0 0] [0 1] [0 2] [1 2] [2 2] [2 1] [2 0] [1 0] [1 1]])

  (fact "Can traverse 4x4 matrix"
    (spiral-indices [[1, 2, 3, 4],
                     [0, 1, 2, 8],
                     [9, 1, 3, -5],
                     [8, 2, 4, -6]]) => [[0 0] [0 1] [0 2] [0 3] [1 3] [2 3] [3 3] [3 2] [3 1] [3 0] [2 0] [1 0] [1 1] [1 2] [2 2] [2 1]]))
