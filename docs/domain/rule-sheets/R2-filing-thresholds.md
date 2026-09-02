# R2 — Filing-obligation thresholds

**Source:** arts. 592, 593 and 594-3 E.T. · DUT 1625 de 2016 art. 1.6.1.13.2.7

## Values

| Tax year | 1.400 UVT | 4.500 UVT |
|---|---|---|
| 2023 | $59.376.800 | $190.854.000 |
| 2024 | $65.891.000 | $211.792.500 |
| 2025 | $69.718.600 | $224.095.500 |

## Formula

    threshold_1400 = 1400 × uvt(tax_year)
    threshold_4500 = 4500 × uvt(tax_year)

    obliged = gross_income     ≥ threshold_1400
           or card_consumption ≥ threshold_1400
           or total_purchases  ≥ threshold_1400
           or deposits         ≥ threshold_1400
           or gross_wealth     ≥ threshold_4500

## Conditions

- Five conditions, any one of which triggers the obligation on its own. Four share the 1.400
  UVT threshold; gross wealth uses 4.500 UVT.
- The comparison is `≥`, not `>`.
- Gross income is the total across the schedules, not labor income alone.
- Gross wealth is measured at 31 December of the tax year.
- Thresholds resolve with the UVT of the tax year being declared, not the one in force at the
  filing date (R1).
- The table gives the exact product. The rounded figures that also circulate — $69.719.000 for
  2025 — are not the threshold the engine compares against (R1).
- **Open:** art. 593 adds a condition for the salaried regime — at least 80% of income arising
  from a labor relationship. Its effect on the five conditions above is not established here.

## Test case

**Given** gross income of $40.000.000, deposits of $30.000.000, purchases of $20.000.000, card
consumption of $15.000.000 and gross wealth of $150.000.000, tax year 2025
**When** the thresholds resolve → no condition is met and the taxpayer is not obliged to file

**Given** the same case with gross wealth of $230.000.000
**When** the thresholds resolve → the 4.500 UVT condition is met on its own and the taxpayer is
obliged, every other figure unchanged

**Given** the first case with gross income of exactly $69.718.600, tax year 2025
**When** the thresholds resolve → the comparison is `≥`, so the taxpayer is obliged
