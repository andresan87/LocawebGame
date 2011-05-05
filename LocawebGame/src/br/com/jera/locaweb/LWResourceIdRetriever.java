package br.com.jera.locaweb;

import br.com.jera.resources.ResourceIdRetriever;

public class LWResourceIdRetriever implements ResourceIdRetriever {

	public int getBmpWhite() {
		return R.drawable.white;
	}

	public int getBmpProgessBar() {
		return R.drawable.progress_bar;
	}

	public int getBmpTower01() {
		return R.drawable.tower01;
	}

	public int getBmpTower02() {
		return R.drawable.tower02;
	}

	public int getBmpTower03() {
		return R.drawable.tower03;
	}

	public int getBmpEnemy01() {
		return R.drawable.virus01;
	}

	public int getBmpEnemy02() {
		return R.drawable.virus02;
	}

	public int getBmpEnemy03() {
		return R.drawable.virus03;
	}

	public int getBmpEnemy04() {
		return R.drawable.virus04;
	}

	public int getBmpTiles() {
		return R.drawable.tiles;
	}

	public int getBmpDeathAnim() {
		return R.drawable.death_blood;
	}

	public int getSfxEnemyDeath() {
		return R.raw.virus_morto;
	}

	public int getBmpNextWaveSymbol() {
		return R.drawable.next_wave_symbol;
	}

	public int getBmpScoreSymbol() {
		return R.drawable.kills;
	}

	public int getBmpScenario() {
		return R.drawable.scenario;
	}

	public int getBmpThemeFont16() {
		return R.drawable.mono24;
	}

	public int getBmpShadow() {
		return R.drawable.shadow;
	}

	public int getBmpRange() {
		return R.drawable.range;
	}

	public int getBmpWeaponProjectile01() {
		return R.drawable.spear;
	}

	public int getBmpWeaponProjectile02() {
		return R.drawable.snap_flash;
	}

	public int getBmpWeaponProjectile03() {
		return R.drawable.fireball;
	}

	public int getBmpWeaponHitEffect01() {
		return R.drawable.shock_animation;
	}

	public int getBmpWeaponHitEffect02() {
		return R.drawable.snap_frame;
	}

	public int getBmpWeaponHitEffect03() {
		return R.drawable.fire_anim;
	}

	public int getBmpScenarioSeam() {
		return R.drawable.cloudsbg;
	}

	public int getBmpMoneySymbol() {
		return R.drawable.money;
	}

	public int getBmpTowerSymbol() {
		return R.drawable.viking_symbol;
	}

	public int getBmpBackButton() {
		return R.drawable.back_button;
	}

	public int getBmpBehaveButtons() {
		return R.drawable.behave_buttons;
	}

	public int getSfxGameOver() {
		return R.raw.perdeu2;
	}

	public int getSfxWeaponTrigger01() {
		return R.raw.servidor_raio;
	}

	public int getSfxWeaponTrigger03() {
		return R.raw.firewall_fogo;
	}

	public int getSfxWeaponHit01() {
		return R.raw.raio_impacto;
	}

	public int getSfxWeaponHit03() {
		return R.raw.fogo_impacto;
	}

	public int getSfxBack() {
		return R.raw.selecionar;
	}

	public int getBmpGameOver() {
		return R.drawable.gameover;
	}

	public int getBmpForwarButton() {
		return R.drawable.forward_arrow;
	}

	public int getBmpDefaultFont16() {
		return R.drawable.simsun16;
	}

	public int getBmpMenuButtons() {
		return R.drawable.full_menu;
	}

	public int getBmpMenuBackground() {
		return R.drawable.cloudsbg;
	}

	public int getBmpMenuLogo() {
		return R.drawable.logo;
	}

	public int getBmpSoundToggle() {
		return R.drawable.soundonoff;
	}

	public int getBmpCompanyLogo() {
		return R.drawable.logo_jera;
	}

	public int getBmpSplashScreenBg() {
		return R.drawable.bg;
	}

	public int getSfxMenuButtonPressed() {
		return R.raw.mudar_selecao;
	}

	public int getBmpSideBar() {
		return R.drawable.side_bar;
	}

	public int getBmpSideBarExtend() {
		return R.drawable.side_bar_extend;
	}

	public int getBmpSideBarBottom() {
		return R.drawable.bottom;
	}

	public int getBmpNextWaveButton() {
		return R.drawable.next_wave_button;
	}

	public int getBmpNextFrameButton() {
		return R.drawable.next_page;
	}

	public int getBmpSkipTutorialButton() {
		return R.drawable.skip_tutorial;
	}

	public int getBmpHelpFrame(int frame) {
		switch (frame) {
		case 0:
			return R.drawable.help01;
		case 1:
			return R.drawable.help02;
		case 2:
		default:
			return R.drawable.help03;
		}
	}

	public int getNumHelpFrames() {
		return 3;
	}

	public int getSfxMenuSong() {
		return R.raw.menu_musica;
	}

	public int getSfxWeaponTrigger02() {
		return R.raw.snapbot_foto;
	}

	public int getSfxWeaponHit02() {
		return R.raw.snapbot_foto;
	}

	public int getBmpSnapshotFX() {
		return R.drawable.snapshotfx;
	}

	public int getSfxTowerDrag() {
		return R.raw.selecionar;
	}

	public int getSfxTowerDrop() {
		return R.raw.mudar_selecao;
	}

	public int getBmpClockHelpCharacter() {
		return R.drawable.programador;
	}

	public int getBmpClockHelpBalloon() {
		return R.drawable.clock_helper_balloon;
	}

	public int getBmpClockHelpTexts() {
		return R.drawable.clock_helper_texts;
	}

	public int getSfxNextLevel() {
		return R.raw.passou_fase;
	}

	public int getSfxVirusAppear01() {
		return R.raw.virus_azul_e_verde;
	}

	public int getSfxVirusAppear02() {
		return R.raw.virus_amarelo;
	}

	public int getBmpGameWon() {
		return R.drawable.gamewon;
	}

	public int getSfxGameWon() {
		return R.raw.venceu_musica;
	}
}
