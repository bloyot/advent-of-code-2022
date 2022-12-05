(ns advent-of-code-2022.day3
  (:require [advent-of-code-2022.core :as core]
            [clojure.set :as set]))

(defn parse-input-part1
  [input-file]
  (map #(partition (/ (count %) 2) %) (core/load-input input-file)))

(defn parse-input-part2
  [input-file]
  (partition 3 (core/load-input input-file)))

(defn priority
  "Return the priority value of a char"
  [c]
  (let [char-value (int c)]
    (if (< 96 char-value)
      (- char-value 96)
      (- char-value 38))))

(defn find-duplicate-priority
  "find the 'priority value' (char int value - 96) of the char that is duplicated between each compartment"
  [[comp1 comp2]]
  (let [set1 (set comp1)
        set2 (set comp2)]
    (priority (first (set/intersection set1 set2)))))

(defn find-duplicate-in-group
  "find duplicate value among a set of rucksacks"
  [sacks]
  (->> sacks
       (map set)
       (apply set/intersection)
       first
       priority))

(defn part1
  [input-file]
  (->> input-file
       parse-input-part1
       (map find-duplicate-priority)
       (reduce +)))

(defn part2
  [input-file]
  (->> input-file
       parse-input-part2
       (map find-duplicate-in-group)
       (reduce +)))


