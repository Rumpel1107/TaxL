# R6 — Individual caps: prepaid health, mortgage interest, AFC / voluntary pension, GMF

**Source:** art. 387 E.T. · art. 119 E.T. · arts. 126-1 and 126-4 E.T. · art. 115 E.T. ·
DIAN Concepto 3591 de 2025 · DUT 1625 de 2016 arts. 1.2.1.20.3–1.2.1.20.4

## Values

| Tax year | 192 UVT — prepaid health | 1.200 UVT — mortgage interest | 3.800 UVT — AFC + voluntary pension |
|---|---|---|---|
| 2023 | $8.143.104 | $50.894.400 | $161.165.600 |
| 2024 | $9.036.480 | $56.478.000 | $178.847.000 |
| 2025 | $9.561.408 | $59.758.800 | $189.236.200 |

## Formula

    prepaid_health    = min(paid, 192 × uvt(tax_year))
    mortgage_interest = min(paid, 1200 × uvt(tax_year))
    afc_and_voluntary = min(contributed, 0.30 × labor_income, 3800 × uvt(tax_year))
    gmf               = 0.50 × gmf_paid

## Conditions

- Each cap resolves against its own concept, before the general cap (R7) applies to their sum.
- The AFC cap carries two conditions and the lower one governs: 30% of labor income, and
  3.800 UVT.
- GMF has no UVT ceiling; the limit is half of what was paid.
- Prepaid health and mortgage interest are stated monthly in the statute — 16 UVT and 100 UVT.
  The engine resolves them annually.
- AFC and voluntary pension are exempt income; prepaid health, mortgage interest and GMF are
  deductions. All four fall inside the general cap (R7), so the distinction does not change the
  arithmetic, only the Form 210 line each lands in.
- Early withdrawal from an AFC account triggers contingent withholding — R17.
- **Open:** whether the 1.200 UVT mortgage cap belongs to the taxpayer or is shared when the
  loan has more than one debtor.

## Test case

**Given** prepaid health paid of $12.000.000 and mortgage interest paid of $8.000.000, tax
year 2025
**When** the caps resolve → `prepaid_health` = $9.561.408, capped; `mortgage_interest` =
$8.000.000, in full

**Given** labor income of $100.000.000 and contributions of $40.000.000, tax year 2025
**When** the cap resolves → the 30% governs and `afc_and_voluntary` = $30.000.000, not
$40.000.000

**Given** labor income of $800.000.000 and contributions of $250.000.000, tax year 2025
**When** the cap resolves → 30% would be $240.000.000, so the 3.800 UVT ceiling governs and
`afc_and_voluntary` = $189.236.200

**Given** GMF paid of $2.000.000
**When** the deduction resolves → $1.000.000, with no ceiling in UVT
