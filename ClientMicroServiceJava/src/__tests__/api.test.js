import { describe, it, expect } from 'vitest'
import { formatDate } from '../services/api'

describe('formatDate', () => {
  it('retourne une chaîne vide pour null', () => {
    expect(formatDate(null)).toBe('')
  })

  it('retourne une chaîne vide pour undefined', () => {
    expect(formatDate(undefined)).toBe('')
  })

  it('retourne une chaîne vide pour une chaîne vide', () => {
    expect(formatDate('')).toBe('')
  })

  it('formate correctement une date en français', () => {
    const result = formatDate('2024-01-15T10:30:00')
    expect(result).toMatch(/15/)
    expect(result).toMatch(/janvier/)
    expect(result).toMatch(/2024/)
  })

  it('inclut les heures et minutes dans le résultat', () => {
    const result = formatDate('2024-06-20T14:45:00')
    expect(result).toMatch(/14/)
    expect(result).toMatch(/45/)
  })
})
