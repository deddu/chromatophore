(ns chromatophore.doo.runner
  (:require [chromatophore.utils-test]
            [doo.runner :refer-macros [doo-tests]]))

(doo-tests 'chromatophore.utils-test)
