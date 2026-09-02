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

    labor_share     = labor_income / gross_income
    exempting_basis = art. 593 (salaried) if labor_share ≥ 0.80, else art. 592
                      — reported only when not obliged

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
- The 80% condition of art. 593 selects the exempting basis, never the outcome: the salaried
  category and the general category carry identical thresholds (DUT 1625 de 2016
  art. 1.6.1.13.2.7), so a salaried person below 80% exits through art. 592 with the same
  figures. Resolved 2026-09-02.
- `labor_share` is derived from the incomes the flow already captures, never asked as a
  question of the user; the exempting basis is reported with the not-obliged result so the
  output cites its legal basis (constitution principle 7).
- The two categories keep separate threshold parameters, equal today: a future divergence
  between them is a parameter change, not a code change.

## Test case

**Given** gross income of $40.000.000, deposits of $30.000.000, purchases of $20.000.000, card
consumption of $15.000.000 and gross wealth of $150.000.000, tax year 2025
**When** the thresholds resolve → no condition is met and the taxpayer is not obliged to file

**Given** the same case with gross wealth of $230.000.000
**When** the thresholds resolve → the 4.500 UVT condition is met on its own and the taxpayer is
obliged, every other figure unchanged

**Given** the first case with gross income of exactly $69.718.600, tax year 2025
**When** the thresholds resolve → the comparison is `≥`, so the taxpayer is obliged

**Given** the first case with labor income of $35.000.000 within the $40.000.000 gross, tax
year 2025
**When** the thresholds resolve → not obliged; labor share is 87,5% ≥ 80%, so the exempting
basis reported is art. 593

**Given** the same case with labor income of $20.000.000
**When** the thresholds resolve → not obliged; labor share is 50% < 80%, so the exempting
basis reported is art. 592
