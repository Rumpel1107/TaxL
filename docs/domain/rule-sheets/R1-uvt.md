# R1 — UVT value per tax year

**Source:** art. 868 E.T. · DIAN Res. 001264 de 2022 (TY2023), 000187 de 2023 (TY2024),
000193 de 2024 (TY2025)

## Values

| Tax year | UVT |
|---|---|
| 2023 | $42.412 |
| 2024 | $47.065 |
| 2025 | $49.799 |

## Formula

    threshold_pesos = uvt_count × uvt(tax_year), rounded to the nearest thousand

## Conditions

- A threshold is stored as its UVT count, never as pesos.
- The applicable UVT is the one of the tax year declared, not the one in force at filing date.
- Rounding to the nearest thousand — art. 868.
- **Open:** whether art. 868 rounding applies to the derived threshold or only to the UVT value.
  The TY2025 filing threshold of 1.400 UVT is published both as $69.719.000 and $69.718.600.

## Test case

**Given** a cap of 790 UVT (R4)
**When** resolved for tax year 2025 → $39.341.000
**When** only the tax year changes to 2024 → $37.181.000, no other parameter edited
