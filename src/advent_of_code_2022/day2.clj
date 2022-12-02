(ns advent-of-code-2022.day2
  (:require [advent-of-code-2022.core :as core]
            [clojure.string :as str]))

(defn parse-input
  [input-file]
  (map #(str/split % #" ") (core/load-input input-file)))

(def results
  {["A" "X"] :draw
   ["A" "Y"] :win
   ["A" "Z"] :loss

   ["B" "X"] :loss
   ["B" "Y"] :draw
   ["B" "Z"] :win

   ["C" "X"] :win
   ["C" "Y"] :loss
   ["C" "Z"] :draw})

(defn win-score
  "determine the round score based on our opponents choice and our choice"
  [opp us]
  (case (get results [opp us])
    :win 6
    :draw 3
    :loss 0
    nil))

(defn shape-score [shape]
  (case shape
    "X" 1
    "Y" 2
    "Z" 3
     nil))

(defn eval-round
  "Evaluate a round, given a sequence that contains our opponents choice and our choice"
  [[opp us]]
  (+ (shape-score us) (win-score opp us)))

(defn part1
  [input-file]
  (reduce + (map eval-round (parse-input input-file))))

;; part 2
(def desired-results
  {["A" "X"] "Z"
   ["A" "Y"] "X"
   ["A" "Z"] "Y"

   ["B" "X"] "X"
   ["B" "Y"] "Y"
   ["B" "Z"] "Z"

   ["C" "X"] "Y"
   ["C" "Y"] "Z"
   ["C" "Z"] "X"})

(defn get-desired-round
  "given the strategy guide choices for the opponent and us, return the round as it should be played"
  [[opp us]]
  [opp (get desired-results [opp us])])

(defn part2
  [input-file]
  (->> (parse-input input-file)
       (map get-desired-round)
       (map eval-round)
       (reduce +)))
