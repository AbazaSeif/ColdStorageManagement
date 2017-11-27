package com.mg.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.Query;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Demand;
import com.mg.csms.beans.InwardStock;
import com.mg.csms.beans.InwardStockItem;
import com.mg.utils.DBQueriesUtils;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * @author Mohak Gupta
 *
 */
public class StockInHandController {

	private List<InwardStock> vyaapariStockList;
	private List<InwardStock> vyaapariStockListFromDB;
	private List<InwardStockItem> stockItemList;
	private List<InwardStockItem> stockItemList1;
	private List<InwardStockItem> stockListUpdated;
	private List<Demand> demandList;
	private List<Demand> demandListToShow;

	private Integer vyaapariIdFromChoiceBox;
	private Integer gadiNoFromChoiceBox = 0;

	@FXML
	private ChoiceBox<String> vyaapari;
	@FXML
	private ChoiceBox<String> gadiNo;
	@FXML
	private TableView<InwardStockItem> stockListView;
	@FXML
	private TableColumn<InwardStockItem, String> itemColdNo;
	@FXML
	private TableColumn<InwardStockItem, String> itemName;
	@FXML
	private TableColumn<InwardStockItem, String> itemQuantity;
	@FXML
	private TableColumn<InwardStockItem, String> itemGadiNo;
	@FXML
	private TableColumn<InwardStockItem, Date> entryDate;
	@FXML
	private TableColumn<InwardStockItem, String> itemExpectedRate;
	@FXML
	private TableColumn<InwardStockItem, String> itemLotNo;
	@FXML
	private TableColumn<InwardStockItem, String> itemColdStore;
	@FXML
	private TableView<Demand> demandListTable;
	@FXML
	private TableColumn<Demand, Date> demandTableDate;
	@FXML
	private TableColumn<Demand, String> demandTableQuantity;

	private DBQueriesUtils dbQueriesUtils;

	@FXML
	protected void initialize() {
		try {
			dbQueriesUtils = DBQueriesUtils.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		vyaapari.setItems(getVyaapariList());

		makeColdStorageList();
		makeVyaapariStockList();
		makeCompleteStockList();
		makeVyaapariChangeAction();
		makeGadiNoChangeAction();
		initializeTableViews();
		addTableRowAction();
		makeDemandTableList();
	}

	@SuppressWarnings("unchecked")
	private void makeDemandTableList() {
		demandList = new ArrayList<>();
		demandListToShow = new ArrayList<>();
		String hql = "FROM Demand";
		Query query = dbQueriesUtils.getSession().createQuery(hql);
		demandList = query.list();
	}

	private void addTableRowAction() {
		stockListView.getSelectionModel().selectedIndexProperty().addListener((num) -> {
			populateDemandTable(stockListView.getSelectionModel().selectedItemProperty().get().getColdNo());
		});
	}

	private void populateDemandTable(Integer coldNo) {
		demandListToShow = demandList.stream().filter(demand -> demand.getColdNo().equals(coldNo))
				.collect(Collectors.toList());
		demandListTable.setItems(FXCollections.observableList(demandListToShow));
	}

	private void makeGadiNoChangeAction() {
		gadiNo.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
				gadiNoFromChoiceBox = 0;
				if (number2.intValue() >= 0)
					gadiNoFromChoiceBox = Integer.parseInt(gadiNo.getItems().get((Integer) number2));
				populateListView();
			}

		});
	}

	private void makeVyaapariChangeAction() {
		vyaapari.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
				vyaapariIdFromChoiceBox = Integer.parseInt(vyaapari.getItems().get((Integer) number2).substring(0,
						vyaapari.getItems().get((Integer) number2).indexOf(":")));
				populateListView();
				gadiNo.setItems(getGadiNoBasedOnVyaapari(vyaapariIdFromChoiceBox));
			}

		});
	}

	private void initializeTableViews() {
		stockListView.setEditable(true);
		entryDate.setCellValueFactory(new PropertyValueFactory<InwardStockItem, Date>("entryDate"));
		itemColdNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("coldNo"));
		itemName.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("item"));
		itemQuantity.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("quantity"));
		itemGadiNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("gadiNo"));
		itemExpectedRate.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("rate"));
		itemLotNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("lotNo"));

		itemColdStore.setCellValueFactory(
				new Callback<CellDataFeatures<InwardStockItem, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<InwardStockItem, String> data) {
						Optional<ColdStorage> coldObject = dbQueriesUtils.getColdStorageList().stream()
								.filter(cold -> cold.getColdId() == data.getValue().getInwardStock().getColdId())
								.findFirst();
						return new SimpleStringProperty(coldObject.get().getColdName());
					}
				});

		demandListTable.setEditable(true);
		demandTableDate.setCellValueFactory(new PropertyValueFactory<Demand, Date>("demandDate"));
		demandTableQuantity.setCellValueFactory(new PropertyValueFactory<Demand, String>("quantity"));
	}

	private ObservableList<String> getGadiNoBasedOnVyaapari(Integer vyaapariId) {
		List<String> gadiNoList = new ArrayList<>();
		getVyaapariStockList();
		vyaapariStockList.forEach(listItem -> gadiNoList.add(listItem.getGadiNo()));
		return FXCollections.observableList(gadiNoList);
	}

	private void populateListView() {
		stockListView.setItems(null);
		stockItemList1 = new ArrayList<>();
		stockListUpdated = new ArrayList<>();
		getVyaapariStockList();
		vyaapariStockList.forEach(vyaapariStock -> makeItemStockListForVyaapari(vyaapariStock));
	}

	private void getVyaapariStockList() {
		vyaapariStockList = vyaapariStockListFromDB.stream()
				.filter(element -> element.getVyaapariId() == vyaapariIdFromChoiceBox).collect(Collectors.toList());
	}

	@SuppressWarnings("unchecked")
	private void makeVyaapariStockList() {
		vyaapariStockListFromDB = new ArrayList<>();
		String hql = "FROM InwardStock";
		vyaapariStockListFromDB = dbQueriesUtils.getSession().createQuery(hql).list();

	}

	private void makeColdStorageList() {
		dbQueriesUtils.makeColdStorageList();
	}

	@SuppressWarnings("unchecked")
	private void makeCompleteStockList() {
		String hql = "FROM InwardStockItem";
		Query query = dbQueriesUtils.getSession().createQuery(hql);
		stockItemList = query.list();
	}

	private void makeItemStockListForVyaapari(InwardStock vyaapariStock) {
		stockItemList1 = stockItemList.stream()
				.filter(element -> element.getInwardStock().getStockId() == vyaapariStock.getStockId())
				.collect(Collectors.toList());
		stockItemList1.forEach(stockItem -> stockListUpdated.add(stockItem));
		if (gadiNoFromChoiceBox != 0)
			stockListUpdated = stockListUpdated.stream()
					.filter(element -> Integer.parseInt(element.getInwardStock().getGadiNo()) == gadiNoFromChoiceBox)
					.collect(Collectors.toList());

		stockListView.setItems(FXCollections.observableList(stockListUpdated));
	}

	private ObservableList<String> getVyaapariList() {
		dbQueriesUtils.makeVyaapariList();
		List<String> vyaapariNameList = new ArrayList<>();
		dbQueriesUtils.getVyaapariArrayList().forEach(vyaapariObject -> vyaapariNameList
				.add(vyaapariObject.getVyaapariId() + ": " + vyaapariObject.getVyaapariName()));
		return FXCollections.observableList(vyaapariNameList);
	}
}
