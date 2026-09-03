# Spec — manual liquidation

> Phase 2. Outside view only. What this slice does as seen by the user; how it is built belongs
> in the plan.
>
> Owner: Rumpel · Written: 2026-09-03

## Purpose and scope

The first vertical slice: the user transcribes their Form 220 and bank certificates by hand, the
system runs the full general-schedule depuration, and returns the liquidation with every line of
Form 210 it produces, each carrying its box number and the article behind it.

**Deliberately not in this slice**

- Exógena upload. Deferred with reason, not cut — its viability is evaluated once the engine works.
- Accounts, login, and any persistence of fiscal figures.
- Certificate OCR, recommendations, and the free/paid tiers.
- The optimizer segment: someone who already knows how to lower their tax draws value only from the
  tiers that detail deductions and recommendations, and this slice offers them nothing they do not
  already have.

## Users

- **The declarant without financial knowledge** — one use case, three segments: never filed, files
  with an accountant and wants to verify, or accepts the DIAN suggested return blindly. Same flow
  for all three.
- **The accountant liquidating third parties' returns** — the same flow plus depth: the formula and
  the normative origin of every criterion applied to every value. Nothing extra is calculated for
  them; the depth layer is constitution principle 7 made visible.

## User flow

1. **Entry screen.** Which documents are needed, how long the flow takes, what to expect from it,
   and that nothing is saved. It also serves whoever lands on the page by mistake.
2. **Tax year.** The user picks 2023, 2024 or 2025. Every parameter follows that choice.
3. **Obligation gate.** With the main figures in, the five thresholds resolve. If not obliged, the
   answer is positive and complete — the thresholds for that year, the user's totals against them,
   and the numeral that exempts them (art. 593 or 592, derived from the labor share, never asked) —
   and the flow stops there, with the option to leave or start again.
4. **Guided transcription** of Form 220 and bank certificates, field by field ("copy the value in
   box X"). The user transcribes documents, not concepts. More than one certificate is supported:
   the user adds each document and the system sums them. The user never sums by hand.
5. **Result.** Balance due or refund, in plain language, with how it was reached.
6. **Detail view**, optional: every line of the general schedule and of the closing block, including
   the ones that come out at zero, each with its Form 210 box number.
7. **Per value**, on demand: how that number was computed and the article that supports it.

## Failure states

| State | What the system detects | What the user sees | What the user can do |
|---|---|---|---|
| Out of scope | On liquidate, a concept or income type the engine does not cover | A notice naming which fields are not covered, and **no figure at all** | Consent to give an email, so support tells them when their case is covered; or leave |
| Missing or malformed field | A required field empty, or a value that is not a figure, as they type | The field flagged with what is missing | Fix it; they cannot move on until it is fixed |
| Values that contradict each other | On liquidate, two values that do not reconcile | The difference and both sources | Decide which one stands — the system never picks a side |
| Session loss | An attempt to leave or reload mid-flow | A warning that what was typed will be lost, since nothing is saved | Confirm and lose it, or stay |

## Acceptance criteria

- **AC1 — Not obliged.** Given totals below all five thresholds, when the main figures are in, then
  the user is told they are not obliged, with the exempting numeral and their figures against each
  threshold, and the flow does not continue.
- **AC2 — Correct liquidation.** Given an in-scope employee, when they liquidate, then every line of
  the general schedule and of the closing block matches the reference return for that tax year to
  the peso. Those lines are what M1 measures.
- **AC3 — Result visible.** Given a completed liquidation, then the user sees balance due or refund
  in plain language, and can open the line-by-line detail carrying each value's Form 210 box number.
- **AC4 — Traceability.** Given any displayed value, when the user opens it, then they see how it
  was computed and the article behind it.
- **AC5 — Out of scope.** Given data the engine does not cover, when they liquidate, then no figure
  is shown, the uncovered fields are named, and the user may consent to give an email so support can
  tell them when their case is covered.
- **AC6 — Missing or malformed field.** Given a required field empty or malformed, when the user
  tries to move on, then they cannot, and they see what is missing.
- **AC7 — Contradicting values.** Given two values that do not reconcile, when they liquidate, then
  the difference is shown and the user decides; the system never picks a side.
- **AC8 — Several certificates.** Given more than one certificate, when they are added, then the
  system sums them and every value stays traceable to its own document.
- **AC9 — Tax year.** Given one of the three years is chosen, then every parameter follows it and no
  value from another year leaks in.
- **AC10 — Session.** Given nothing is stored, when the user tries to leave or reload, then they are
  warned they will lose what they typed.
- **AC11 — Disclaimer.** The result is presented as educational, carries no liability, and is not
  the official return.
- **AC12 — Language.** Given the user picks English or Spanish, then every user-facing text appears
  in that language. No text is hardcoded in either one.

## What is stored

Guest only, no accounts.

- **Never stored:** the user's fiscal figures, and no documents. Nothing is persisted until its
  custody can be guaranteed.
- **Stored:** the email the user explicitly consents to give at the out-of-scope notice, and the
  report of which fields were not covered — the field, never the value. That report is how coverage
  gets prioritized case by case, which is what constitution principle 9 requires and no document
  described until now.

## Open questions

| # | Question | Status | Resolution / why deferred |
|---|---|---|---|
| 1 | Accounts and persistence of the user's figures | deferred | They make no sense before the tool can charge. That is the moment to revisit Law 1581 (PLAN risk R-6) and the free tier |
| 2 | Exógena upload as the entry point | deferred | Evaluated once the engine works, together with the one or two non-accounting questions that would replace the transcription |
| 3 | The exact wording of the disclaimer (AC11) | deferred | Gated on the legal-boundary research item, which also gates the tier X copy |
| 4 | R4 — art. 206 par. 5 extends the 25% exemption to professional fees, and the slice's scope includes an employee with fees income opting for it | deferred | Owned by the rule-flags roadmap item. It blocks building the fees path, not writing this spec |
| 5 | Does this slice ship both interface languages, or only one with the mechanism in place? | resolved | Both, English and Spanish, from the first slice, with every user-facing string behind a key and the language selectable by the user |

---

**Exit gate**

- [x] Acceptance criteria in Given/When/Then form
- [x] Failure states enumerated, not implied
- [x] Every open question resolved or explicitly deferred
- [x] No technical decisions leaked into this document
- [x] No `[NEEDS CLARIFICATION]` marker is left unresolved
- [x] Every claim here was confirmed in conversation before it was written down
