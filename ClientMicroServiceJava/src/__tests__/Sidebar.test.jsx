import { describe, it, expect } from 'vitest'
import { render, screen } from '@testing-library/react'
import { MemoryRouter } from 'react-router-dom'
import Sidebar from '../components/layout/Sidebar'

const renderSidebar = (initialRoute = '/') =>
  render(
    <MemoryRouter initialEntries={[initialRoute]}>
      <Sidebar />
    </MemoryRouter>
  )

describe('Sidebar', () => {
  it('affiche le logo MSJ', () => {
    renderSidebar()
    expect(screen.getByText('MSJ')).toBeInTheDocument()
  })

  it('affiche les 4 liens de navigation', () => {
    renderSidebar()
    expect(screen.getByText('Accueil')).toBeInTheDocument()
    expect(screen.getByText('Créer Event')).toBeInTheDocument()
    expect(screen.getByText('Événements')).toBeInTheDocument()
    expect(screen.getByText('Mes Inscriptions')).toBeInTheDocument()
  })

  it('le lien Accueil pointe vers /', () => {
    renderSidebar()
    const link = screen.getByText('Accueil').closest('a')
    expect(link).toHaveAttribute('href', '/')
  })

  it('le lien Créer Event pointe vers /create-event', () => {
    renderSidebar()
    const link = screen.getByText('Créer Event').closest('a')
    expect(link).toHaveAttribute('href', '/create-event')
  })

  it('le lien Événements pointe vers /events', () => {
    renderSidebar()
    const link = screen.getByText('Événements').closest('a')
    expect(link).toHaveAttribute('href', '/events')
  })

  it('le lien Mes Inscriptions pointe vers /my-registrations', () => {
    renderSidebar()
    const link = screen.getByText('Mes Inscriptions').closest('a')
    expect(link).toHaveAttribute('href', '/my-registrations')
  })

  it('le lien actif reçoit la classe active', () => {
    renderSidebar('/events')
    const link = screen.getByText('Événements').closest('a')
    expect(link).toHaveClass('active')
  })

  it('affiche la section footer avec Utilisateur', () => {
    renderSidebar()
    expect(screen.getByText('Utilisateur')).toBeInTheDocument()
  })
})
